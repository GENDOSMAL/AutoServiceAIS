using System.Text.Json.Serialization;

namespace AutoSeviceClient.Models
{
    public class BaseResponse<T>
    {
        [JsonPropertyName("status")]
        public int Status { get; set; }

        [JsonPropertyName("object")]
        public T Data { get; set; }
    }
}