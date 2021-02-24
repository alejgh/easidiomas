using System;
using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Hosting;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Hosting;
using Microsoft.Extensions.Logging;
using NLog.Config;
using NLog.Targets;
using PostsService.Consumers;
using PostsService.Data;
using PostsService.Kafka;

namespace PostsService
{
    public class Startup
    {

        // This method gets called by the runtime. Use this method to add services to the container.
        public void ConfigureServices(IServiceCollection services)
        {
            ConfigureKafka(services);
            ConfigureLogging(services);

            services.AddDbContext<DataContext>();
            services.AddControllers();
        }

        // This method gets called by the runtime. Use this method to configure the HTTP request pipeline.
        public void Configure(IApplicationBuilder app, IWebHostEnvironment env, ILoggerFactory loggerFactory)
        {
            if (env.IsDevelopment()) app.UseDeveloperExceptionPage();

            using (var serviceScope = app.ApplicationServices.GetService<IServiceScopeFactory>().CreateScope())
            {
                var services = serviceScope.ServiceProvider;
                var context = services.GetRequiredService<DataContext>();
                context.Database.EnsureDeleted(); // for testing purposes
                context.Database.EnsureCreated();
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
