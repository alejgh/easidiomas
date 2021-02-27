import React, {useState} from 'react';
import { StyleSheet, Text, View, TextInput, Image,TouchableOpacity } from 'react-native';
import SignUpLanguajePicker from '../auth/SignUpLanguajePicker';

export default function EditProfileScreen({navigation}){

    const [native,setNative] = useState('Native');
    const [learning1,setLearning1] = useState('Learning');
    const [learning2,setLearning2] = useState('Learning');

    
    const signUp = function(){
      //TODOO
      navigation.navigate("Login");
    }
    

    const changeProfilePic = function(){
      //TODOO
    }
    


    return (
      <View style={styles.container}>
         <TouchableOpacity onPress={changeProfilePic}>
          <Image style={styles.avatar} source={{uri: 'https://www.bootdey.com/img/Content/avatar/avatar2.png'}}/>
        </TouchableOpacity>  
        <View style={styles.inputView} >
          <TextInput  
            style={styles.inputText}
            placeholder="Name *" 
            placeholderTextColor="#E1E8ED"/>
        </View>
        <View style={styles.inputView} >
          <TextInput  
            style={styles.inputText}
            placeholder="Surname *" 
            placeholderTextColor="#E1E8ED"/>
        </View>
        <View style={styles.inputView} >
          <TextInput  
            style={styles.inputText}
            placeholder="BirthDate *" 
            placeholderTextColor="#E1E8ED"/>
        </View>

        <View style={styles.languajesContainer} >
            <SignUpLanguajePicker labelTag='Native*'/>
            <SignUpLanguajePicker labelTag='Learning*'/>
            <SignUpLanguajePicker labelTag='Learning*'/>
        </View>

  
        <TouchableOpacity style={styles.signupBtn} onPress={signUp}>
          <Text style={styles.signupText}>Update</Text>
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
  avatar: {
    width: 130,
    height: 130,
    borderRadius: 63,
    borderWidth: 4,
    borderColor: "white",
    marginBottom:30,

  },
  inputView:{
    width:"82%",
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
  signupBtn:{
    width:"80%",
    backgroundColor:"#1DA1F2",
    borderRadius:25,
    height:50,
    alignItems:"center",
    justifyContent:"center",
    marginTop:40,
    marginBottom:10
  },
  signupText:{
    color:"white",
    fontSize:18,
  },
  languajesContainer:{
    flex:0.4,
    flexDirection:'row',
    justifyContent:'center',
    width:"100%",
  }
});