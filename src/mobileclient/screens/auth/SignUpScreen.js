import React, {useState} from 'react';
import { StyleSheet, Text, View, TextInput, Image,TouchableOpacity } from 'react-native';
import SignUpLanguajePicker from './SignUpLanguajePicker';

export default function SignUpScreen({navigation}){

    const [native,setNative] = useState('Native');
    const [learning1,setLearning1] = useState('Learning');
    const [learning2,setLearning2] = useState('Learning');

    
    const next = function(){
      //TODOO
      navigation.navigate("Sign Up Profile");
    }
    

    const changeProfilePic = function(){
      //TODOO
    }
    


    return (
      <View style={styles.container}>
        <Text style={styles.logo}>Easidiomas</Text>
        <View style={styles.inputView} >
          <TextInput  
            style={styles.inputText}
            placeholder="Username *" 
            placeholderTextColor="#E1E8ED"/>
        </View>
        <View style={styles.inputView} >
          <TextInput  
            secureTextEntry
            style={styles.inputText}
            placeholder="Password *" 
            placeholderTextColor="#E1E8ED"/>
        </View>

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
  }
});