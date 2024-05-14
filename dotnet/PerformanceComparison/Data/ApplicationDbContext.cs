using Microsoft.EntityFrameworkCore;

namespace PerformanceComparison.Data;

public sealed class ApplicationDbContext(DbContextOptions<ApplicationDbContext> options)
    : DbContext(options)
{
    /// <summary>
    /// Gets the set of <see cref="PersonEntity"/>.
    /// </summary>
    public DbSet<PersonEntity> Persons { get; private init; } = null!;
}
