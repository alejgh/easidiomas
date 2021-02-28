import React, {useState} from 'react';
import { StyleSheet, Text, View, TextInput, Image,TouchableOpacity } from 'react-native';
import SignUpLanguajePicker from './SignUpLanguajePicker';

export default function SignUpScreen({route,navigation}){

    const {errors} = route.params;
    const [username,setUsername] = useState('asd');
    const [usernameView,setUsernameView] = useState(view);
    const [password,setPassword] = useState('asd');
    const [passwordView,setPasswordView] = useState(view);
  
    const next = function(){
      if(!updateErrors())
        navigation.navigate("Sign Up Profile",{username:username,password:password});
    }

    const updateErrors = function(){
      let hasErrors = false;
      if(username.length<=0){
        hasErrors=true;
        setUsernameView(errorsView);
      }else{
        setUsernameView(view);
      }

      if(password.length<=0){
        hasErrors=true;
        setPasswordView(errorsView)
      }else{
        setPasswordView(view);
      }
      return hasErrors;
    }
    
  
    return (
      <View style={styles.container}>
        <Text style={styles.logo}>Easidiomas</Text>
        <View style={usernameView} >
          <TextInput  
            onChangeText={text => setUsername(text)}
            defaultValue={username}
            style={styles.inputText}
            placeholder="Username *" 
            placeholderTextColor="#E1E8ED"/>
        </View>
        <View style={passwordView} >
          <TextInput 
            onChangeText={text => setPassword(text)}
            defaultValue={password} 
            secureTextEntry
            style={styles.inputText}
            placeholder="Password *" 
            placeholderTextColor="#E1E8ED"/>
        </View>
        <Text style={styles.errors}>{errors}</Text>
        <TouchableOpacity style={styles.nextBtn} onPress={next}>
          <Text style={styles.nextText}>Next</Text>
        </TouchableOpacity>

      </View>
    );
}



const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#1b2836',
    alignItems: 'center',
    justifyContent: 'center',
  }, 
  logo:{
    fontWeight:"bold",
    fontSize:50,
    color:"#1DA1F2",
    marginBottom:40
  },
  avatar: {
    width: 130,
    height: 130,
    borderRadius: 63,
    borderWidth: 4,
    borderColor: "white",
    marginBottom:30,

  },
  inputView:{
    width:"80%",
    backgroundColor:"#465881",
    borderRadius:25,
    height:50,
    marginBottom:20,
    justifyContent:"center",
    padding:20
  },
  inputText:{
    height:50,
    color:"white"
  },
  forgot:{
    color:"white",
    fontSize:11
  },
  nextBtn:{
    width:"80%",
    backgroundColor:"#1DA1F2",
    borderRadius:25,
    height:50,
    alignItems:"center",
    justifyContent:"center",
    marginTop:40,
    marginBottom:10
  },
  nextText:{
    color:"white",
    fontSize:18
  },
  languajesContainer:{
    flex:0.2,
    width:300,
    flexDirection:'row',
    justifyContent:'space-around',
    alignItems:'center'
  },
  errors:{
    color:'red'
  }
});



export const view = { width:"82%",
  backgroundColor:"#465881",
  borderRadius:25,
  height:50,
  marginBottom:20,
  justifyContent:"center",
  padding:20
}

export const errorsView = { width:"82%",
  backgroundColor:"#465881",
  borderRadius:25,
  height:50,
  marginBottom:20,
  justifyContent:"center",
  padding:20,
  borderWidth:1,
  borderColor:'red'
}