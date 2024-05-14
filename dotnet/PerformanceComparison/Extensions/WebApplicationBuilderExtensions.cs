using Microsoft.EntityFrameworkCore;
using Npgsql;
using PerformanceComparison.Data;
using PerformanceComparison.Services;

namespace PerformanceComparison.Extensions;

public static class WebApplicationBuilderExtensions
{
    public static WebApplicationBuilder AddLogging(this WebApplicationBuilder builder)
    {
        builder.Logging.ClearProviders();

        return builder;
    }

    public static WebApplicationBuilder AddDatabase(this WebApplicationBuilder builder)
    {
        var dataSourceBuilder = new NpgsqlDataSourceBuilder(
            builder.Configuration.GetConnectionString(nameof(ApplicationDbContext)));

        builder.Services.AddPooledDbContextFactory<ApplicationDbContext>(optionsBuilder =>
            optionsBuilder
                .UseNpgsql(dataSourceBuilder.Build())
                .UseQueryTrackingBehavior(QueryTrackingBehavior.NoTracking));

        return builder;
    }

    public static WebApplicationBuilder AddServices(this WebApplicationBuilder builder)
    {
        builder.Services.AddScoped<IPersonService, PersonService>();

        return builder;
    }
}