import React, {useState,useEffect } from 'react';
import { StyleSheet, Text, View, TextInput, Image,TouchableOpacity } from 'react-native';
import SignUpLanguajePicker from './SignUpLanguajePicker';
import * as ImagePicker from 'expo-image-picker';

export default function SignUpProfileScreen({navigation}){

    const [native,setNative] = useState('Native');
    const [learning1,setLearning1] = useState('Learning');
    const [learning2,setLearning2] = useState('Learning');

    const [image, setImage] = useState('https://www.bootdey.com/img/Content/avatar/avatar2.png');
    
    const signUp = function(){
      //TODOO
      navigation.navigate("Login");
    }
    

    const pickImage = async () => {

      if (Platform.OS !== 'web') {
        const { status } = await ImagePicker.requestMediaLibraryPermissionsAsync();
        if (status !== 'granted') {
          alert('Sorry, we need camera roll permissions to make this work!');
        }
      }

      let result = await ImagePicker.launchImageLibraryAsync({
        mediaTypes: ImagePicker.MediaTypeOptions.All,
        allowsEditing: true,
        aspect: [4, 3],
        quality: 1,
      });
  

      if (!result.cancelled) {
        setImage(result.uri);
      }

    };

    return (
      <View style={styles.container}>
         <TouchableOpacity onPress={pickImage}>
          <Image style={styles.avatar} source={{uri: image}}/>
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
          <Text style={styles.signupText}>SIGN UP</Text>
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