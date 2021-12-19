using System;
using System.Text.Json.Serialization;

namespace AutoSeviceClient.Models
{
    public class Client
    {
        [JsonPropertyName("id")]
        public long Id { get; set; }

        [JsonPropertyName("name")]
        public string Name { get; set; }

        [JsonPropertyName("lastName")]
        public string LastName { get; set; }

        [JsonPropertyName("patronymics")]
        public string Patronymics { get; set; }

        [JsonPropertyName("birthDay")]
        public DateTime? BirthDay { get; set; }

        [JsonPropertyName("email")]
        public string Email { get; set; }

        [JsonPropertyName("phone")]
        public string PhoneNumber { get; set; }

        [JsonPropertyName("preferCommunicationType")]
        public string PreferCommunicationType { get; set; }

        [JsonPropertyName("autoType")]
        public string AutoType { get; set; }

        [JsonPropertyName("autoNumber")]
        public string AutoNumber { get; set; }
    }
}