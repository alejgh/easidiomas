import React, {useContext,useState} from 'react';
import {AppContext} from '../../App';
import { StyleSheet, Text, View, TextInput, TouchableOpacity, ActivityIndicator} from 'react-native';

export default function LoginScreen({navigation}){

    const context = useContext(AppContext);
    const {REQUEST_URI} = context.CONFIG;
    const [username,setUsername] = useState('mistermboy');
    const [usernameView,setUsernameView] = useState(view);
    const [password,setPassword] = useState('12345');
    const [passwordView,setPasswordView] = useState(view);
    const [loading,setLoading] = useState(false);

    const signUp = function(){
        navigation.navigate("Sign Up",{errors:''});
    }

    const logIn = async function(){
      let hasErrors = updateErrors();
      if(hasErrors)
        return;
      
      setLoading(true)

      let response = await (await fetch(REQUEST_URI+'/auth/token',{
        method: 'POST',
        disableAllSecurity: true,
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({username:username,password:password})
      })).json();

      console.log(response)

      let tokenPermission = response.tokenPermissions_;
      let token = response.tokenGenerated_
       // TODO
      // manejar logins failed
      if(tokenPermission== -1){
        console.log('Fail Login')
        return
      }
        
      context.setToken(token)
      let user = (await getUser(token)).users[0];
      context.setUser(user);
      setLoading(false)
    }


    const getUser = async function(token){
      return (await fetch(REQUEST_URI+'/users?username='+username,{
        method: 'GET',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json',
          'token':token
        }})).json();
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
            placeholder="Email *" 
            placeholderTextColor="#E1E8ED"
            />
        </View>
        <View style={passwordView} >
          <TextInput  
            onChangeText={text => setPassword(text)}
            defaultValue={password}
            secureTextEntry
            style={styles.inputText}
            placeholder="Password *" 
            placeholderTextColor="#E1E8ED"
           />
        </View>
        {loading ? 
          <View style={styles.btnsContainer}>
            <ActivityIndicator  size="large" color="#fff"/>
          </View> : 

          <View style={styles.btnsContainer}>
            <TouchableOpacity style={styles.loginBtn} onPress={logIn}>
              <Text style={styles.loginText}>LOG IN</Text>
            </TouchableOpacity>
            <TouchableOpacity onPress={signUp}>
              <Text style={styles.signupText}>Sign Up</Text>
            </TouchableOpacity>
          </View>
        }
  
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
  loginBtn:{
    width:"80%",
    backgroundColor:"#1DA1F2",
    borderRadius:25,
    height:50,
    alignItems:"center",
    justifyContent:"center",
    marginTop:40,
    marginBottom:10
  },
  loginText:{
    color:"white",
    fontSize:18
  },
  signupText:{
    color:"white",
    marginTop:20,
    fontSize:15
  },
  errors:{
    color:'red'
  },
  btnsContainer:{
    width:"100%",
    borderRadius:25,
    height:50,
    alignItems:"center",
    justifyContent:"center",
    marginTop:40,
    marginBottom:10
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