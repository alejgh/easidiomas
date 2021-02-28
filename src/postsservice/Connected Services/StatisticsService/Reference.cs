﻿//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated by a tool.
//
//     Changes to this file may cause incorrect behavior and will be lost if
//     the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

namespace StatisticsService
{
    
    
    [System.CodeDom.Compiler.GeneratedCodeAttribute("Microsoft.Tools.ServiceModel.Svcutil", "2.0.2")]
    [System.ServiceModel.ServiceContractAttribute(Namespace="http://service.statisticsservice.easidiomas.com/", ConfigurationName="StatisticsService.IStatisticsService")]
    public interface IStatisticsService
    {
        
        [System.ServiceModel.OperationContractAttribute(Action="", ReplyAction="*")]
        [System.ServiceModel.XmlSerializerFormatAttribute(SupportFaults=true)]
        System.Threading.Tasks.Task<StatisticsService.registerTranslationCreatedEventResponse> registerTranslationCreatedEventAsync(StatisticsService.registerTranslationCreatedEvent request);
        
        [System.ServiceModel.OperationContractAttribute(Action="", ReplyAction="*")]
        [System.ServiceModel.XmlSerializerFormatAttribute(SupportFaults=true)]
        System.Threading.Tasks.Task<StatisticsService.registerUserCreatedEventResponse> registerUserCreatedEventAsync(StatisticsService.registerUserCreatedEvent request);
        
        [System.ServiceModel.OperationContractAttribute(Action="", ReplyAction="*")]
        [System.ServiceModel.XmlSerializerFormatAttribute(SupportFaults=true)]
        System.Threading.Tasks.Task<StatisticsService.getSystemStatisticsResponse> getSystemStatisticsAsync(StatisticsService.getSystemStatistics request);
        
        [System.ServiceModel.OperationContractAttribute(Action="", ReplyAction="*")]
        [System.ServiceModel.XmlSerializerFormatAttribute(SupportFaults=true)]
        System.Threading.Tasks.Task<StatisticsService.registerPostCreatedEventResponse> registerPostCreatedEventAsync(StatisticsService.registerPostCreatedEvent request);
        
        [System.ServiceModel.OperationContractAttribute(Action="", ReplyAction="*")]
        [System.ServiceModel.XmlSerializerFormatAttribute(SupportFaults=true)]
        System.Threading.Tasks.Task<StatisticsService.registerTextToSpeechCreatedEventResponse> registerTextToSpeechCreatedEventAsync(StatisticsService.registerTextToSpeechCreatedEvent request);
        
        [System.ServiceModel.OperationContractAttribute(Action="", ReplyAction="*")]
        [System.ServiceModel.XmlSerializerFormatAttribute(SupportFaults=true)]
        System.Threading.Tasks.Task registerChatCreatedEventAsync();
        
        [System.ServiceModel.OperationContractAttribute(Action="", ReplyAction="*")]
        [System.ServiceModel.XmlSerializerFormatAttribute(SupportFaults=true)]
        System.Threading.Tasks.Task<StatisticsService.getUserStatisticsResponse> getUserStatisticsAsync(StatisticsService.getUserStatistics request);
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("Microsoft.Tools.ServiceModel.Svcutil", "2.0.2")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.ServiceModel.MessageContractAttribute(WrapperName="registerTranslationCreatedEvent", WrapperNamespace="http://service.statisticsservice.easidiomas.com/", IsWrapped=true)]
    public partial class registerTranslationCreatedEvent
    {
        
        [System.ServiceModel.MessageBodyMemberAttribute(Namespace="http://service.statisticsservice.easidiomas.com/", Order=0)]
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified)]
        public string arg0;
        
        public registerTranslationCreatedEvent()
        {
        }
        
        public registerTranslationCreatedEvent(string arg0)
        {
            this.arg0 = arg0;
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("Microsoft.Tools.ServiceModel.Svcutil", "2.0.2")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.ServiceModel.MessageContractAttribute(WrapperName="registerTranslationCreatedEventResponse", WrapperNamespace="http://service.statisticsservice.easidiomas.com/", IsWrapped=true)]
    public partial class registerTranslationCreatedEventResponse
    {
        
        public registerTranslationCreatedEventResponse()
        {
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("Microsoft.Tools.ServiceModel.Svcutil", "2.0.2")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.ServiceModel.MessageContractAttribute(WrapperName="registerUserCreatedEvent", WrapperNamespace="http://service.statisticsservice.easidiomas.com/", IsWrapped=true)]
    public partial class registerUserCreatedEvent
    {
        
        [System.ServiceModel.MessageBodyMemberAttribute(Namespace="http://service.statisticsservice.easidiomas.com/", Order=0)]
        [System.Xml.Serialization.XmlElementAttribute("arg0", Form=System.Xml.Schema.XmlSchemaForm.Unqualified)]
        public string[] arg0;
        
        [System.ServiceModel.MessageBodyMemberAttribute(Namespace="http://service.statisticsservice.easidiomas.com/", Order=1)]
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified)]
        public string arg1;
        
        public registerUserCreatedEvent()
        {
        }
        
        public registerUserCreatedEvent(string[] arg0, string arg1)
        {
            this.arg0 = arg0;
            this.arg1 = arg1;
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("Microsoft.Tools.ServiceModel.Svcutil", "2.0.2")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.ServiceModel.MessageContractAttribute(WrapperName="registerUserCreatedEventResponse", WrapperNamespace="http://service.statisticsservice.easidiomas.com/", IsWrapped=true)]
    public partial class registerUserCreatedEventResponse
    {
        
        public registerUserCreatedEventResponse()
        {
        }
    }
    
    /// <remarks/>
    [System.CodeDom.Compiler.GeneratedCodeAttribute("Microsoft.Tools.ServiceModel.Svcutil", "2.0.2")]
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.Xml.Serialization.XmlTypeAttribute(Namespace="http://service.statisticsservice.easidiomas.com/")]
    public partial class systemStatistics
    {
        
        private int createdChatsField;
        
        private systemStatisticsEntry[] createdPostsField;
        
        private registeredUsersData registeredUsersField;
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=0)]
        public int createdChats
        {
            get
            {
                return this.createdChatsField;
            }
            set
            {
                this.createdChatsField = value;
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlArrayAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=1)]
        [System.Xml.Serialization.XmlArrayItemAttribute("entry", Form=System.Xml.Schema.XmlSchemaForm.Unqualified, IsNullable=false)]
        public systemStatisticsEntry[] createdPosts
        {
            get
            {
                return this.createdPostsField;
            }
            set
            {
                this.createdPostsField = value;
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=2)]
        public registeredUsersData registeredUsers
        {
            get
            {
                return this.registeredUsersField;
            }
            set
            {
                this.registeredUsersField = value;
            }
        }
    }
    
    /// <remarks/>
    [System.CodeDom.Compiler.GeneratedCodeAttribute("Microsoft.Tools.ServiceModel.Svcutil", "2.0.2")]
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.Xml.Serialization.XmlTypeAttribute(AnonymousType=true, Namespace="http://service.statisticsservice.easidiomas.com/")]
    public partial class systemStatisticsEntry
    {
        
        private string keyField;
        
        private int valueField;
        
        private bool valueFieldSpecified;
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=0)]
        public string key
        {
            get
            {
                return this.keyField;
            }
            set
            {
                this.keyField = value;
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=1)]
        public int value
        {
            get
            {
                return this.valueField;
            }
            set
            {
                this.valueField = value;
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlIgnoreAttribute()]
        public bool valueSpecified
        {
            get
            {
                return this.valueFieldSpecified;
            }
            set
            {
                this.valueFieldSpecified = value;
            }
        }
    }
    
    /// <remarks/>
    [System.CodeDom.Compiler.GeneratedCodeAttribute("Microsoft.Tools.ServiceModel.Svcutil", "2.0.2")]
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.Xml.Serialization.XmlTypeAttribute(Namespace="http://service.statisticsservice.easidiomas.com/")]
    public partial class userStatistics
    {
        
        private userStatisticsEntry[] createdPostsField;
        
        private int textToSpeechMadeField;
        
        private int translationsMadeField;
        
        private string userIdField;
        
        /// <remarks/>
        [System.Xml.Serialization.XmlArrayAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=0)]
        [System.Xml.Serialization.XmlArrayItemAttribute("entry", Form=System.Xml.Schema.XmlSchemaForm.Unqualified, IsNullable=false)]
        public userStatisticsEntry[] createdPosts
        {
            get
            {
                return this.createdPostsField;
            }
            set
            {
                this.createdPostsField = value;
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=1)]
        public int textToSpeechMade
        {
            get
            {
                return this.textToSpeechMadeField;
            }
            set
            {
                this.textToSpeechMadeField = value;
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=2)]
        public int translationsMade
        {
            get
            {
                return this.translationsMadeField;
            }
            set
            {
                this.translationsMadeField = value;
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=3)]
        public string userId
        {
            get
            {
                return this.userIdField;
            }
            set
            {
                this.userIdField = value;
            }
        }
    }
    
    /// <remarks/>
    [System.CodeDom.Compiler.GeneratedCodeAttribute("Microsoft.Tools.ServiceModel.Svcutil", "2.0.2")]
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.Xml.Serialization.XmlTypeAttribute(AnonymousType=true, Namespace="http://service.statisticsservice.easidiomas.com/")]
    public partial class userStatisticsEntry
    {
        
        private string keyField;
        
        private int valueField;
        
        private bool valueFieldSpecified;
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=0)]
        public string key
        {
            get
            {
                return this.keyField;
            }
            set
            {
                this.keyField = value;
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=1)]
        public int value
        {
            get
            {
                return this.valueField;
            }
            set
            {
                this.valueField = value;
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlIgnoreAttribute()]
        public bool valueSpecified
        {
            get
            {
                return this.valueFieldSpecified;
            }
            set
            {
                this.valueFieldSpecified = value;
            }
        }
    }
    
    /// <remarks/>
    [System.CodeDom.Compiler.GeneratedCodeAttribute("Microsoft.Tools.ServiceModel.Svcutil", "2.0.2")]
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.Xml.Serialization.XmlTypeAttribute(Namespace="http://service.statisticsservice.easidiomas.com/")]
    public partial class registeredUsersData
    {
        
        private registeredUsersDataEntry[] learningField;
        
        private registeredUsersDataEntry1[] nnativeField;
        
        private int totalField;
        
        /// <remarks/>
        [System.Xml.Serialization.XmlArrayAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=0)]
        [System.Xml.Serialization.XmlArrayItemAttribute("entry", Form=System.Xml.Schema.XmlSchemaForm.Unqualified, IsNullable=false)]
        public registeredUsersDataEntry[] learning
        {
            get
            {
                return this.learningField;
            }
            set
            {
                this.learningField = value;
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlArrayAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=1)]
        [System.Xml.Serialization.XmlArrayItemAttribute("entry", Form=System.Xml.Schema.XmlSchemaForm.Unqualified, IsNullable=false)]
        public registeredUsersDataEntry1[] nnative
        {
            get
            {
                return this.nnativeField;
            }
            set
            {
                this.nnativeField = value;
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=2)]
        public int total
        {
            get
            {
                return this.totalField;
            }
            set
            {
                this.totalField = value;
            }
        }
    }
    
    /// <remarks/>
    [System.CodeDom.Compiler.GeneratedCodeAttribute("Microsoft.Tools.ServiceModel.Svcutil", "2.0.2")]
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.Xml.Serialization.XmlTypeAttribute(AnonymousType=true, Namespace="http://service.statisticsservice.easidiomas.com/")]
    public partial class registeredUsersDataEntry
    {
        
        private string keyField;
        
        private int valueField;
        
        private bool valueFieldSpecified;
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=0)]
        public string key
        {
            get
            {
                return this.keyField;
            }
            set
            {
                this.keyField = value;
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=1)]
        public int value
        {
            get
            {
                return this.valueField;
            }
            set
            {
                this.valueField = value;
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlIgnoreAttribute()]
        public bool valueSpecified
        {
            get
            {
                return this.valueFieldSpecified;
            }
            set
            {
                this.valueFieldSpecified = value;
            }
        }
    }
    
    /// <remarks/>
    [System.CodeDom.Compiler.GeneratedCodeAttribute("Microsoft.Tools.ServiceModel.Svcutil", "2.0.2")]
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.Xml.Serialization.XmlTypeAttribute(AnonymousType=true, Namespace="http://service.statisticsservice.easidiomas.com/")]
    public partial class registeredUsersDataEntry1
    {
        
        private string keyField;
        
        private int valueField;
        
        private bool valueFieldSpecified;
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=0)]
        public string key
        {
            get
            {
                return this.keyField;
            }
            set
            {
                this.keyField = value;
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=1)]
        public int value
        {
            get
            {
                return this.valueField;
            }
            set
            {
                this.valueField = value;
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlIgnoreAttribute()]
        public bool valueSpecified
        {
            get
            {
                return this.valueFieldSpecified;
            }
            set
            {
                this.valueFieldSpecified = value;
            }
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("Microsoft.Tools.ServiceModel.Svcutil", "2.0.2")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.ServiceModel.MessageContractAttribute(WrapperName="getSystemStatistics", WrapperNamespace="http://service.statisticsservice.easidiomas.com/", IsWrapped=true)]
    public partial class getSystemStatistics
    {
        
        public getSystemStatistics()
        {
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("Microsoft.Tools.ServiceModel.Svcutil", "2.0.2")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.ServiceModel.MessageContractAttribute(WrapperName="getSystemStatisticsResponse", WrapperNamespace="http://service.statisticsservice.easidiomas.com/", IsWrapped=true)]
    public partial class getSystemStatisticsResponse
    {
        
        [System.ServiceModel.MessageBodyMemberAttribute(Namespace="http://service.statisticsservice.easidiomas.com/", Order=0)]
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified)]
        public StatisticsService.systemStatistics @return;
        
        public getSystemStatisticsResponse()
        {
        }
        
        public getSystemStatisticsResponse(StatisticsService.systemStatistics @return)
        {
            this.@return = @return;
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("Microsoft.Tools.ServiceModel.Svcutil", "2.0.2")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.ServiceModel.MessageContractAttribute(WrapperName="registerPostCreatedEvent", WrapperNamespace="http://service.statisticsservice.easidiomas.com/", IsWrapped=true)]
    public partial class registerPostCreatedEvent
    {
        
        [System.ServiceModel.MessageBodyMemberAttribute(Namespace="http://service.statisticsservice.easidiomas.com/", Order=0)]
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified)]
        public string arg0;
        
        [System.ServiceModel.MessageBodyMemberAttribute(Namespace="http://service.statisticsservice.easidiomas.com/", Order=1)]
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified)]
        public string arg1;
        
        public registerPostCreatedEvent()
        {
        }
        
        public registerPostCreatedEvent(string arg0, string arg1)
        {
            this.arg0 = arg0;
            this.arg1 = arg1;
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("Microsoft.Tools.ServiceModel.Svcutil", "2.0.2")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.ServiceModel.MessageContractAttribute(WrapperName="registerPostCreatedEventResponse", WrapperNamespace="http://service.statisticsservice.easidiomas.com/", IsWrapped=true)]
    public partial class registerPostCreatedEventResponse
    {
        
        public registerPostCreatedEventResponse()
        {
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("Microsoft.Tools.ServiceModel.Svcutil", "2.0.2")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.ServiceModel.MessageContractAttribute(WrapperName="registerTextToSpeechCreatedEvent", WrapperNamespace="http://service.statisticsservice.easidiomas.com/", IsWrapped=true)]
    public partial class registerTextToSpeechCreatedEvent
    {
        
        [System.ServiceModel.MessageBodyMemberAttribute(Namespace="http://service.statisticsservice.easidiomas.com/", Order=0)]
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified)]
        public string arg0;
        
        public registerTextToSpeechCreatedEvent()
        {
        }
        
        public registerTextToSpeechCreatedEvent(string arg0)
        {
            this.arg0 = arg0;
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("Microsoft.Tools.ServiceModel.Svcutil", "2.0.2")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.ServiceModel.MessageContractAttribute(WrapperName="registerTextToSpeechCreatedEventResponse", WrapperNamespace="http://service.statisticsservice.easidiomas.com/", IsWrapped=true)]
    public partial class registerTextToSpeechCreatedEventResponse
    {
        
        public registerTextToSpeechCreatedEventResponse()
        {
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("Microsoft.Tools.ServiceModel.Svcutil", "2.0.2")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.ServiceModel.MessageContractAttribute(WrapperName="getUserStatistics", WrapperNamespace="http://service.statisticsservice.easidiomas.com/", IsWrapped=true)]
    public partial class getUserStatistics
    {
        
        [System.ServiceModel.MessageBodyMemberAttribute(Namespace="http://service.statisticsservice.easidiomas.com/", Order=0)]
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified)]
        public string arg0;
        
        public getUserStatistics()
        {
        }
        
        public getUserStatistics(string arg0)
        {
            this.arg0 = arg0;
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("Microsoft.Tools.ServiceModel.Svcutil", "2.0.2")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.ServiceModel.MessageContractAttribute(WrapperName="getUserStatisticsResponse", WrapperNamespace="http://service.statisticsservice.easidiomas.com/", IsWrapped=true)]
    public partial class getUserStatisticsResponse
    {
        
        [System.ServiceModel.MessageBodyMemberAttribute(Namespace="http://service.statisticsservice.easidiomas.com/", Order=0)]
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified)]
        public StatisticsService.userStatistics @return;
        
        public getUserStatisticsResponse()
        {
        }
        
        public getUserStatisticsResponse(StatisticsService.userStatistics @return)
        {
            this.@return = @return;
        }
    }
    
    [System.CodeDom.Compiler.GeneratedCodeAttribute("Microsoft.Tools.ServiceModel.Svcutil", "2.0.2")]
    public interface IStatisticsServiceChannel : StatisticsService.IStatisticsService, System.ServiceModel.IClientChannel
    {
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("Microsoft.Tools.ServiceModel.Svcutil", "2.0.2")]
    public partial class StatisticsServiceClient : System.ServiceModel.ClientBase<StatisticsService.IStatisticsService>, StatisticsService.IStatisticsService
    {
        
        /// <summary>
        /// Implement this partial method to configure the service endpoint.
        /// </summary>
        /// <param name="serviceEndpoint">The endpoint to configure</param>
        /// <param name="clientCredentials">The client credentials</param>
        static partial void ConfigureEndpoint(System.ServiceModel.Description.ServiceEndpoint serviceEndpoint, System.ServiceModel.Description.ClientCredentials clientCredentials);
        
        public StatisticsServiceClient() : 
                base(StatisticsServiceClient.GetDefaultBinding(), StatisticsServiceClient.GetDefaultEndpointAddress())
        {
            this.Endpoint.Name = EndpointConfiguration.IStatisticsServicePort.ToString();
            ConfigureEndpoint(this.Endpoint, this.ClientCredentials);
        }
        
        public StatisticsServiceClient(EndpointConfiguration endpointConfiguration) : 
                base(StatisticsServiceClient.GetBindingForEndpoint(endpointConfiguration), StatisticsServiceClient.GetEndpointAddress(endpointConfiguration))
        {
            this.Endpoint.Name = endpointConfiguration.ToString();
            ConfigureEndpoint(this.Endpoint, this.ClientCredentials);
        }
        
        public StatisticsServiceClient(EndpointConfiguration endpointConfiguration, string remoteAddress) : 
                base(StatisticsServiceClient.GetBindingForEndpoint(endpointConfiguration), new System.ServiceModel.EndpointAddress(remoteAddress))
        {
            this.Endpoint.Name = endpointConfiguration.ToString();
            ConfigureEndpoint(this.Endpoint, this.ClientCredentials);
        }
        
        public StatisticsServiceClient(EndpointConfiguration endpointConfiguration, System.ServiceModel.EndpointAddress remoteAddress) : 
                base(StatisticsServiceClient.GetBindingForEndpoint(endpointConfiguration), remoteAddress)
        {
            this.Endpoint.Name = endpointConfiguration.ToString();
            ConfigureEndpoint(this.Endpoint, this.ClientCredentials);
        }
        
        public StatisticsServiceClient(System.ServiceModel.Channels.Binding binding, System.ServiceModel.EndpointAddress remoteAddress) : 
                base(binding, remoteAddress)
        {
        }
        
        [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
        System.Threading.Tasks.Task<StatisticsService.registerTranslationCreatedEventResponse> StatisticsService.IStatisticsService.registerTranslationCreatedEventAsync(StatisticsService.registerTranslationCreatedEvent request)
        {
            return base.Channel.registerTranslationCreatedEventAsync(request);
        }
        
        public System.Threading.Tasks.Task<StatisticsService.registerTranslationCreatedEventResponse> registerTranslationCreatedEventAsync(string arg0)
        {
            StatisticsService.registerTranslationCreatedEvent inValue = new StatisticsService.registerTranslationCreatedEvent();
            inValue.arg0 = arg0;
            return ((StatisticsService.IStatisticsService)(this)).registerTranslationCreatedEventAsync(inValue);
        }
        
        [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
        System.Threading.Tasks.Task<StatisticsService.registerUserCreatedEventResponse> StatisticsService.IStatisticsService.registerUserCreatedEventAsync(StatisticsService.registerUserCreatedEvent request)
        {
            return base.Channel.registerUserCreatedEventAsync(request);
        }
        
        public System.Threading.Tasks.Task<StatisticsService.registerUserCreatedEventResponse> registerUserCreatedEventAsync(string[] arg0, string arg1)
        {
            StatisticsService.registerUserCreatedEvent inValue = new StatisticsService.registerUserCreatedEvent();
            inValue.arg0 = arg0;
            inValue.arg1 = arg1;
            return ((StatisticsService.IStatisticsService)(this)).registerUserCreatedEventAsync(inValue);
        }
        
        [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
        System.Threading.Tasks.Task<StatisticsService.getSystemStatisticsResponse> StatisticsService.IStatisticsService.getSystemStatisticsAsync(StatisticsService.getSystemStatistics request)
        {
            return base.Channel.getSystemStatisticsAsync(request);
        }
        
        public System.Threading.Tasks.Task<StatisticsService.getSystemStatisticsResponse> getSystemStatisticsAsync()
        {
            StatisticsService.getSystemStatistics inValue = new StatisticsService.getSystemStatistics();
            return ((StatisticsService.IStatisticsService)(this)).getSystemStatisticsAsync(inValue);
        }
        
        [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
        System.Threading.Tasks.Task<StatisticsService.registerPostCreatedEventResponse> StatisticsService.IStatisticsService.registerPostCreatedEventAsync(StatisticsService.registerPostCreatedEvent request)
        {
            return base.Channel.registerPostCreatedEventAsync(request);
        }
        
        public System.Threading.Tasks.Task<StatisticsService.registerPostCreatedEventResponse> registerPostCreatedEventAsync(string arg0, string arg1)
        {
            StatisticsService.registerPostCreatedEvent inValue = new StatisticsService.registerPostCreatedEvent();
            inValue.arg0 = arg0;
            inValue.arg1 = arg1;
            return ((StatisticsService.IStatisticsService)(this)).registerPostCreatedEventAsync(inValue);
        }
        
        [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
        System.Threading.Tasks.Task<StatisticsService.registerTextToSpeechCreatedEventResponse> StatisticsService.IStatisticsService.registerTextToSpeechCreatedEventAsync(StatisticsService.registerTextToSpeechCreatedEvent request)
        {
            return base.Channel.registerTextToSpeechCreatedEventAsync(request);
        }
        
        public System.Threading.Tasks.Task<StatisticsService.registerTextToSpeechCreatedEventResponse> registerTextToSpeechCreatedEventAsync(string arg0)
        {
            StatisticsService.registerTextToSpeechCreatedEvent inValue = new StatisticsService.registerTextToSpeechCreatedEvent();
            inValue.arg0 = arg0;
            return ((StatisticsService.IStatisticsService)(this)).registerTextToSpeechCreatedEventAsync(inValue);
        }
        
        public System.Threading.Tasks.Task registerChatCreatedEventAsync()
        {
            return base.Channel.registerChatCreatedEventAsync();
        }
        
        [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
        System.Threading.Tasks.Task<StatisticsService.getUserStatisticsResponse> StatisticsService.IStatisticsService.getUserStatisticsAsync(StatisticsService.getUserStatistics request)
        {
            return base.Channel.getUserStatisticsAsync(request);
        }
        
        public System.Threading.Tasks.Task<StatisticsService.getUserStatisticsResponse> getUserStatisticsAsync(string arg0)
        {
            StatisticsService.getUserStatistics inValue = new StatisticsService.getUserStatistics();
            inValue.arg0 = arg0;
            return ((StatisticsService.IStatisticsService)(this)).getUserStatisticsAsync(inValue);
        }
        
        public virtual System.Threading.Tasks.Task OpenAsync()
        {
            return System.Threading.Tasks.Task.Factory.FromAsync(((System.ServiceModel.ICommunicationObject)(this)).BeginOpen(null, null), new System.Action<System.IAsyncResult>(((System.ServiceModel.ICommunicationObject)(this)).EndOpen));
        }
        
        public virtual System.Threading.Tasks.Task CloseAsync()
        {
            return System.Threading.Tasks.Task.Factory.FromAsync(((System.ServiceModel.ICommunicationObject)(this)).BeginClose(null, null), new System.Action<System.IAsyncResult>(((System.ServiceModel.ICommunicationObject)(this)).EndClose));
        }
        
        private static System.ServiceModel.Channels.Binding GetBindingForEndpoint(EndpointConfiguration endpointConfiguration)
        {
            if ((endpointConfiguration == EndpointConfiguration.IStatisticsServicePort))
            {
                System.ServiceModel.BasicHttpBinding result = new System.ServiceModel.BasicHttpBinding();
                result.MaxBufferSize = int.MaxValue;
                result.ReaderQuotas = System.Xml.XmlDictionaryReaderQuotas.Max;
                result.MaxReceivedMessageSize = int.MaxValue;
                result.AllowCookies = true;
                return result;
            }
            throw new System.InvalidOperationException(string.Format("Could not find endpoint with name \'{0}\'.", endpointConfiguration));
        }
        
        private static System.ServiceModel.EndpointAddress GetEndpointAddress(EndpointConfiguration endpointConfiguration)
        {
            if ((endpointConfiguration == EndpointConfiguration.IStatisticsServicePort))
            {
                return new System.ServiceModel.EndpointAddress("http://156.35.82.22:5000/soapws/statistics");
            }
            throw new System.InvalidOperationException(string.Format("Could not find endpoint with name \'{0}\'.", endpointConfiguration));
        }
        
        private static System.ServiceModel.Channels.Binding GetDefaultBinding()
        {
            return StatisticsServiceClient.GetBindingForEndpoint(EndpointConfiguration.IStatisticsServicePort);
        }
        
        private static System.ServiceModel.EndpointAddress GetDefaultEndpointAddress()
        {
            return StatisticsServiceClient.GetEndpointAddress(EndpointConfiguration.IStatisticsServicePort);
        }
        
        public enum EndpointConfiguration
        {
            
            IStatisticsServicePort,
        }
    }
}