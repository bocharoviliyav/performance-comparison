using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace PerformanceComparison.Data.Entities;

[Table("person")]
public sealed class PersonEntity
{
    [Key]
    [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
    [Column("id")]
    public int Id { get; init; }

    [Column("name")] 
    public required string Name { get; init; }

    [Column("address1")] 
    public required string Address1 { get; init; }

    [Column("address2")] 
    public required string Address2 { get; init; }

    [Column("city")] 
    public required string City { get; init; }
}