using Microsoft.EntityFrameworkCore;
using PerformanceComparison.Data;

namespace PerformanceComparison.Services;

public sealed class PersonService(IDbContextFactory<ApplicationDbContext> contextFactory) 
    : IPersonService
{
    public async Task<PersonEntity?> GetByIdAsync(long id, CancellationToken cancellationToken = default)
    {
        await using var context = await contextFactory.CreateDbContextAsync(cancellationToken);
        return await context.Persons.FirstOrDefaultAsync(x => x.Id == id, cancellationToken: cancellationToken);
    }

    public async Task<PersonEntity?> CreateOrUpdateAsync(PersonEntity person, CancellationToken cancellationToken = default)
    {
        await using var context = await contextFactory.CreateDbContextAsync(cancellationToken);
        await using var transaction = await context.Database.BeginTransactionAsync(cancellationToken);

        try
        {
            var entity = await context.Persons.FirstOrDefaultAsync(x => x.Id == person.Id, cancellationToken: cancellationToken);
            var result = entity is null
                ? (await context.Persons.AddAsync(person, cancellationToken)).Entity
                : context.Persons.Update(person).Entity;

            await transaction.CommitAsync(cancellationToken);
            return result;
        }
        catch (Exception)
        {
            await transaction.RollbackAsync(cancellationToken);
            return null;
        }
    }
}
