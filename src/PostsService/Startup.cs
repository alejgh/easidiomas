using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Hosting;
using Microsoft.AspNetCore.HttpsPolicy;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Hosting;
using Microsoft.Extensions.Logging;
using Microsoft.OpenApi.Models;
using NLog.Config;
using NLog.Extensions.Logging;
using NLog.Targets;
using NLog.Web;
using PostsService.Consumers;
using PostsService.Kafka;

namespace PostsService
{
    public class Startup
    {
        public Startup(IConfiguration configuration)
        {
            Configuration = configuration;
        }

        public IConfiguration Configuration { get; }

        // This method gets called by the runtime. Use this method to add services to the container.
        public void ConfigureServices(IServiceCollection services)
        {
            ConfigureKafka(services);
            ConfigureLogging(services);

            services.AddControllers();
            services.AddSwaggerGen(c =>
            {
                c.SwaggerDoc("v1", new OpenApiInfo { Title = "PostsService", Version = "v1" });
            });
        }

        // This method gets called by the runtime. Use this method to configure the HTTP request pipeline.
        public void Configure(IApplicationBuilder app, IWebHostEnvironment env, ILoggerFactory loggerFactory)
        {
            if (env.IsDevelopment())
            {
                app.UseDeveloperExceptionPage();
                app.UseSwagger();
                app.UseSwaggerUI(c => c.SwaggerEndpoint("/swagger/v1/swagger.json", "PostsService v1"));
            }


            app.UseRouting();

            app.UseAuthorization();

            app.UseEndpoints(endpoints =>
            {
                endpoints.MapControllers();
            });
        }

        private void ConfigureKafka(IServiceCollection services)
        {
            // create producers
            services.AddSingleton<KafkaClientHandle>();
            services.AddSingleton<KafkaProducer<long, string>>();   // communication with services: ("post_id", "text")
            services.AddSingleton<KafkaProducer<string, string>>(); // logs: ("service_name", "log")

            // create consumers of messages from text processing systems
            services.AddHostedService<LanguageDetectionConsumer>();
            services.AddHostedService<OffensiveTextDetectionConsumer>();
            services.AddHostedService<TopicsConsumer>();
        }

        private void ConfigureLogging(IServiceCollection services)
        {
            Target.Register<KafkaLoggerTarget>("KafkaLogger");

            // workaround to use dependency injection + NLog custom target
            // see https://stackoverflow.com/questions/42033398/custom-nlog-layoutrenderer-with-constructor-using-dependency-injection/42101946#42101946
            // for more information
            ConfigurationItemFactory.Default.CreateInstance = (Type type) =>
            {
                if (type == typeof(KafkaLoggerTarget))
                    return new KafkaLoggerTarget(services.BuildServiceProvider().GetService<KafkaClientHandle>());
                else return Activator.CreateInstance(type);
            };
        }
    }
}
