#pragma checksum "G:\Study\Kursovik\src\client\AutoSeviceClient\AutoSeviceClient\Pages\About.razor" "{ff1816ec-aa5e-4d10-87f7-6f4963833460}" "a1f7fac9c34271797efd24e67bce8411319add29"
// <auto-generated/>
#pragma warning disable 1591
namespace AutoSeviceClient.Pages
{
    #line hidden
    using System;
    using System.Collections.Generic;
    using System.Linq;
    using System.Threading.Tasks;
    using Microsoft.AspNetCore.Components;
#nullable restore
#line 1 "G:\Study\Kursovik\src\client\AutoSeviceClient\AutoSeviceClient\_Imports.razor"
using System.Net.Http;

#line default
#line hidden
#nullable disable
#nullable restore
#line 2 "G:\Study\Kursovik\src\client\AutoSeviceClient\AutoSeviceClient\_Imports.razor"
using Microsoft.AspNetCore.Authorization;

#line default
#line hidden
#nullable disable
#nullable restore
#line 3 "G:\Study\Kursovik\src\client\AutoSeviceClient\AutoSeviceClient\_Imports.razor"
using Microsoft.AspNetCore.Components.Authorization;

#line default
#line hidden
#nullable disable
#nullable restore
#line 4 "G:\Study\Kursovik\src\client\AutoSeviceClient\AutoSeviceClient\_Imports.razor"
using Microsoft.AspNetCore.Components.Forms;

#line default
#line hidden
#nullable disable
#nullable restore
#line 5 "G:\Study\Kursovik\src\client\AutoSeviceClient\AutoSeviceClient\_Imports.razor"
using Microsoft.AspNetCore.Components.Routing;

#line default
#line hidden
#nullable disable
#nullable restore
#line 6 "G:\Study\Kursovik\src\client\AutoSeviceClient\AutoSeviceClient\_Imports.razor"
using Microsoft.AspNetCore.Components.Web;

#line default
#line hidden
#nullable disable
#nullable restore
#line 7 "G:\Study\Kursovik\src\client\AutoSeviceClient\AutoSeviceClient\_Imports.razor"
using Microsoft.AspNetCore.Components.Web.Virtualization;

#line default
#line hidden
#nullable disable
#nullable restore
#line 8 "G:\Study\Kursovik\src\client\AutoSeviceClient\AutoSeviceClient\_Imports.razor"
using Microsoft.JSInterop;

#line default
#line hidden
#nullable disable
#nullable restore
#line 9 "G:\Study\Kursovik\src\client\AutoSeviceClient\AutoSeviceClient\_Imports.razor"
using AutoSeviceClient;

#line default
#line hidden
#nullable disable
#nullable restore
#line 10 "G:\Study\Kursovik\src\client\AutoSeviceClient\AutoSeviceClient\_Imports.razor"
using AutoSeviceClient.Shared;

#line default
#line hidden
#nullable disable
#nullable restore
#line 11 "G:\Study\Kursovik\src\client\AutoSeviceClient\AutoSeviceClient\_Imports.razor"
using MudBlazor;

#line default
#line hidden
#nullable disable
    [Microsoft.AspNetCore.Components.RouteAttribute("/about")]
    public partial class About : Microsoft.AspNetCore.Components.ComponentBase
    {
        #pragma warning disable 1998
        protected override void BuildRenderTree(Microsoft.AspNetCore.Components.Rendering.RenderTreeBuilder __builder)
        {
            __Blazor.AutoSeviceClient.Pages.About.TypeInference.CreateMudTable_0(__builder, 0, 1, 
#nullable restore
#line 4 "G:\Study\Kursovik\src\client\AutoSeviceClient\AutoSeviceClient\Pages\About.razor"
                  true

#line default
#line hidden
#nullable disable
            , 2, 
#nullable restore
#line 4 "G:\Study\Kursovik\src\client\AutoSeviceClient\AutoSeviceClient\Pages\About.razor"
                                    Breakpoint.Sm

#line default
#line hidden
#nullable disable
            , 3, 
#nullable restore
#line 4 "G:\Study\Kursovik\src\client\AutoSeviceClient\AutoSeviceClient\Pages\About.razor"
                                                                          Color.Info

#line default
#line hidden
#nullable disable
            , 4, (__builder2) => {
                __builder2.OpenComponent<MudBlazor.MudTh>(5);
                __builder2.AddAttribute(6, "ChildContent", (Microsoft.AspNetCore.Components.RenderFragment)((__builder3) => {
                    __builder3.AddMarkupContent(7, "О чём");
                }
                ));
                __builder2.CloseComponent();
                __builder2.AddMarkupContent(8, "\r\n        ");
                __builder2.OpenComponent<MudBlazor.MudTh>(9);
                __builder2.AddAttribute(10, "ChildContent", (Microsoft.AspNetCore.Components.RenderFragment)((__builder3) => {
                    __builder3.AddMarkupContent(11, "Что");
                }
                ));
                __builder2.CloseComponent();
            }
            , 12, (context) => (__builder2) => {
                __builder2.AddMarkupContent(13, "<th>342423</th>");
            }
            );
        }
        #pragma warning restore 1998
#nullable restore
#line 16 "G:\Study\Kursovik\src\client\AutoSeviceClient\AutoSeviceClient\Pages\About.razor"
       
    private int currentCount = 0;

    private void IncrementCount()
    {
        currentCount++;
    }

#line default
#line hidden
#nullable disable
    }
}
namespace __Blazor.AutoSeviceClient.Pages.About
{
    #line hidden
    internal static class TypeInference
    {
        public static void CreateMudTable_0<T>(global::Microsoft.AspNetCore.Components.Rendering.RenderTreeBuilder __builder, int seq, int __seq0, global::System.Boolean __arg0, int __seq1, global::MudBlazor.Breakpoint __arg1, int __seq2, global::MudBlazor.Color __arg2, int __seq3, global::Microsoft.AspNetCore.Components.RenderFragment __arg3, int __seq4, global::Microsoft.AspNetCore.Components.RenderFragment<T> __arg4)
        {
        __builder.OpenComponent<global::MudBlazor.MudTable<T>>(seq);
        __builder.AddAttribute(__seq0, "Hover", __arg0);
        __builder.AddAttribute(__seq1, "Breakpoint", __arg1);
        __builder.AddAttribute(__seq2, "LoadingProgressColor", __arg2);
        __builder.AddAttribute(__seq3, "HeaderContent", __arg3);
        __builder.AddAttribute(__seq4, "RowTemplate", __arg4);
        __builder.CloseComponent();
        }
    }
}
#pragma warning restore 1591
