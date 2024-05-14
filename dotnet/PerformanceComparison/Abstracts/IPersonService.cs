namespace PerformanceComparison.Abstracts;

public interface IPersonService
{
    Task<PersonEntity?> GetByIdAsync(long id, CancellationToken cancellationToken = default);
    
    Task<PersonEntity?> CreateOrUpdateAsync(PersonEntity person, CancellationToken cancellationToken = default);
}