import React, {useContext} from 'react';
import {AppContext} from '../../App';
import { StyleSheet, Text, View, TextInput, TouchableOpacity } from 'react-native';


export default function LoginScreen({navigation}){

  const context = useContext(AppContext);

    const signUp = function(){
        navigation.navigate("Sign Up");
    }

    const signIn = function(){
     context.setUser('usuario');
    }

    return (
      <View style={styles.container}>
        <Text style={styles.logo}>Easidiomas</Text>
        <View style={styles.inputView} >
          <TextInput  
            style={styles.inputText}
            placeholder="Email *" 
            placeholderTextColor="#E1E8ED"
            />
        </View>
        <View style={styles.inputView} >
          <TextInput  
            secureTextEntry
            style={styles.inputText}
            placeholder="Password *" 
            placeholderTextColor="#E1E8ED"
           />
        </View>
        <TouchableOpacity style={styles.loginBtn} onPress={signIn}>
          <Text style={styles.loginText}>LOGIN</Text>
        </TouchableOpacity>
        <TouchableOpacity onPress={signUp}>
          <Text style={styles.signupText}>Sign Up</Text>
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
  }
});