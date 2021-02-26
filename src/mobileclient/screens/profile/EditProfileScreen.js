import React, { Component, useState } from 'react';
import {
  StyleSheet,
  Text,
  View,
  Image,
  TouchableOpacity
} from 'react-native';
import TextInput from 'react-native-material-textinput'
import SignUpLanguajePicker from '../auth/SignUpLanguajePicker';


export default function EditProfileScreen(props) {

    const {navigation,avatar,name,surname,username,email,nativeCode,learningCode} = props;
    const [newName,setNewName] = useState(name);
    const [newSurname,setNewSurname] = useState(surname);
    const [newUsername,setNewUsername] = useState(username);
    const [newEmail,setNewEmail] = useState(email);

    const editProfile = function(){
      navigation.navigate('Edit Profile');
    }



    return (
      <View style={styles.container}>
        <TextInput
          labelColor='#fff'
          labelActiveTop={-20}
          labelActiveColor='#1DA1F2'
          underlineActiveColor= '#1DA1F2'
          color='#fff'
          label="Name"
          value={newName}
          onChangeText={val => setNewName(val)}
        />
        <TextInput
          labelColor='#fff'
          labelActiveColor='#1DA1F2'
          underlineActiveColor= '#1DA1F2'
          color='#fff'
            label="Surname"
            value={newSurname}
            onChangeText={val => setNewName(val)}
          />
          <TextInput
            labelColor='#fff'
            labelActiveColor='#1DA1F2'
            underlineActiveColor= '#1DA1F2'
            color='#fff'
            label="Usermane"
            value={newUsername}
            onChangeText={val => setNewUsername(val)}
          />
          <TextInput
            labelColor='#fff'
            labelActiveColor='#1DA1F2'
            underlineActiveColor= '#1DA1F2'
            color='#fff'
            label="Email"
            value={newEmail}
            onChangeText={val => setNewEmail(val)}
          />

        <View style={styles.bodyContent}>
            <View style={styles.languajesContainer}>
                  <SignUpLanguajePicker labelTag='Native'/>
                  <SignUpLanguajePicker labelTag='Learning'/>
                  <SignUpLanguajePicker labelTag='Learning'/>
              </View>

                <TouchableOpacity style={styles.buttonContainer} onPress={()=> editProfile()}>
                        <Text style={styles.usernameLabel}>Save Profile</Text>  
                </TouchableOpacity>   
          </View>
      </View>
    );
}

const styles = StyleSheet.create({
    container:{
      flex: 1,
      backgroundColor:'#1b2836',
      justifyContent:'center',
      padding:20
    },
    languajesContainer:{
      flex:0.2,
      width:300,
      flexDirection:'row',
      justifyContent:'space-around',
      alignItems:'center'
    },
    bodyContent: {
      marginTop:10,
      alignItems: 'center',
      padding:30,
    },
    buttonContainer: {
      height:45,
      flexDirection: 'row',
      justifyContent: 'center',
      alignItems: 'center',
      marginTop:40,
      width:250,
      borderRadius:30,
      backgroundColor: "#1DA1F2",
    },
    usernameLabel:{
      fontSize:18,
      color:'#fff'
    },
});


EditProfileScreen.defaultProps  = {
  name:'Pablo',
  surname: 'Menéndez Suárez',
  username:'@mistermboy',
  email:'pabloyo97@hotmail.com',
  nativeCode:'ES',
  learningCode:'EN'
}