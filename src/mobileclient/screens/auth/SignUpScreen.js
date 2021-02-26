import React, {useState} from 'react';
import { StyleSheet, Text, View, TextInput, TouchableOpacity } from 'react-native';
import SignUpLanguajePicker from './SignUpLanguajePicker';

export default function SignUpScreen({navigation}){

    const [native,setNative] = useState('Native');
    const [learning1,setLearning1] = useState('Learning');
    const [learning2,setLearning2] = useState('Learning');

    
    const signUp = function(){
      navigation.navigate("Login");
    }
    
    const logIn = function(){
      navigation.navigate("Login");
    }
    


    return (
      <View style={styles.container}>
        <Text style={styles.logo}>Easidiomas</Text>
        <View style={styles.inputView} >
          <TextInput  
            style={styles.inputText}
            placeholder="Name" 
            placeholderTextColor="#7b9dc7"/>
        </View>
        <View style={styles.inputView} >
          <TextInput  
            style={styles.inputText}
            placeholder="Surname" 
            placeholderTextColor="#7b9dc7"/>
        </View>
        <View style={styles.inputView} >
          <TextInput  
            style={styles.inputText}
            placeholder="Username" 
            placeholderTextColor="#7b9dc7"/>
        </View>
        <View style={styles.inputView} >
          <TextInput  
            style={styles.inputText}
            placeholder="Email" 
            placeholderTextColor="#7b9dc7"/>
        </View>
        <View style={styles.inputView} >
          <TextInput  
            secureTextEntry
            style={styles.inputText}
            placeholder="Password..." 
            placeholderTextColor="#7b9dc7"/>
        </View>

        <View style={styles.languajesContainer}>
            <SignUpLanguajePicker labelTag='Native'/>
            <SignUpLanguajePicker labelTag='Learning'/>
            <SignUpLanguajePicker labelTag='Learning'/>
        </View>
        
        

        <TouchableOpacity style={styles.loginBtn} onPress={signUp}>
          <Text style={styles.loginText}>SIGNUP</Text>
        </TouchableOpacity>
        <TouchableOpacity onPress={logIn}>
          <Text style={styles.loginText}>Login</Text>
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
    marginBottom:20
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
    color:"white"
  },
  languajesContainer:{
    flex:0.2,
    width:300,
    flexDirection:'row',
    justifyContent:'space-around',
    alignItems:'center'
  }
});