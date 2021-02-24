﻿using System;
using System.Collections.Generic;
using System.IO;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.Logging;
using Newtonsoft.Json;
using PostsService.Model;

namespace PostsService.Data
{
    public class DataContext : DbContext
    {
        public DbSet<Post> Posts { get; set; }

        private readonly IConfiguration config;
        private readonly ILogger<DataContext> logger;

        public DataContext(IConfiguration config, ILogger<DataContext> logger)
        {
            this.config = config;
            this.logger = logger;
        }

        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            logger.LogDebug("Configuring connection to Postgres...");
            optionsBuilder.UseNpgsql(this.config["POSTGRES_CONNECTION"]);
        }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            SeedData(modelBuilder);
        }

        /// <summary>
        /// Adds seed data to the database.
        ///
        /// This method should only be used for testing/development purposes. It reads
        /// the posts from the devposts.json files, and adds them to the database if
        /// needed.
        /// </summary>
        /// <param name="modelBuilder">ModelBuilder instance </param>
        private void SeedData(ModelBuilder modelBuilder)
        {
            logger.LogDebug("Adding seed data...");

            using (StreamReader r = new StreamReader(config["DEV_POSTS_FILE"]))
            {
                logger.LogDebug($"Reading {config["DEV_POSTS_FILE"]} file...");
                string jsonContent = r.ReadToEnd();
                logger.LogDebug("Deserializing json into posts...");
                IEnumerable<Post> posts = JsonConvert.DeserializeObject<Post[]>(jsonContent);
                logger.LogDebug("Adding posts to database...");
                modelBuilder.Entity<Post>().HasData(posts);
                logger.LogDebug("Seed data has been successfully added!");
            }
        }
    }
}
