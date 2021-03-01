import React, {useState,useEffect, useContext } from 'react';
import { StyleSheet, Text, View, TextInput, Image,TouchableOpacity,ActivityIndicator } from 'react-native';
import SignUpLanguajePicker from './SignUpLanguajePicker';
import * as ImagePicker from 'expo-image-picker';
import * as FileSystem from 'expo-file-system';
import { AppContext } from '../../App';
import DateTimePicker from '@react-native-community/datetimepicker';


export default function SignUpProfileScreen({route,navigation}){

    const {REQUEST_URI} = (useContext(AppContext)).CONFIG;
    const {username,password} = route.params;

    const [name,setName] = useState('');
    const [nameView,setNameView] = useState(view);
    const [surname,setSurname] = useState('');
    const [surnameView,setSurnameView] = useState(view);
    const [birthDate,setBirthdate] = useState('Birth Date *');
    const [birthDateView,setBirthDateView] = useState(view);
    const [showDate, setShowDate] = useState(false);
    const [native,setNative] = useState('');
    const [nativeView,setNativeView] = useState(langView);
    const [learning1,setLearning1] = useState('');
    const [leraning1View,setLeraning1View] = useState(langView);
    const [learning2,setLearning2] = useState('');
    const [leraning2View,setLeraning2View] = useState(langView);

    const [image, setImage] = useState('https://www.bootdey.com/img/Content/avatar/avatar2.png');
    const [base64Image, setBase64Image] = useState('');
    const [errors,setErrors] = useState('');
    const [loading,setLoading] = useState(false);
    
    const signUp = async function(){
      let errors = updateErrors();
      
      if(!errors){
        setLoading(true)
        let response = await fetch(REQUEST_URI+'/users',{
          method: 'post',
          headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
          },
          body: JSON.stringify({
            base64Image:base64Image,
            username:username,
            password:password,
            name:name,
            surname:surname,
            birthDate: new Date(birthDate).getTime(),
            speaks:native,
            learning:[learning1,learning2]})
        });
  
        setLoading(false)
        if(response.status == 201)
          navigation.navigate("Login");
        else
          navigation.navigate("Sign Up",{errors:'Username already taken'});
      }
    }
    

    const updateErrors = function(){
      let hasErrors = false;
      if(name.length<=0){
        hasErrors=true;
        setNameView(errorsView);
      }else{
        setNameView(view);
      }

      if(surname.length<=0){
        hasErrors=true;
        setSurnameView(errorsView)
      }else{
        setSurnameView(view);
      }

      if(birthDate == 'Birth Date *'){
        hasErrors=true;
        setBirthDateView(errorsView)
      }else{
        setBirthDateView(view);
      }


      if(native.length<=0){
        hasErrors=true;
        setNativeView(langErrorsVies)
      }else{
        setNativeView(langView);
      }

      if(learning1.length<=0){
        hasErrors=true;
        setLeraning1View(langErrorsVies)
      }else{
        setLeraning1View(langView);
      }

      if(learning2.length<=0){
        hasErrors=true;
        setLeraning2View(langErrorsVies)
      }else{
        setLeraning2View(langView);
      }
      return hasErrors;
    }


    const pickImage = async () => {

      if (Platform.OS !== 'web') {
        const { status } = await ImagePicker.requestMediaLibraryPermissionsAsync();
        if (status !== 'granted') {
          alert('Sorry, we need camera roll permissions to make this work!');
        }
      }

      let result = await ImagePicker.launchImageLibraryAsync({
        mediaTypes: ImagePicker.MediaTypeOptions.Images,
        allowsEditing: true,
        aspect: [4, 3],
        quality: 1,
      });
  
      if (!result.cancelled) {
        if(!result.uri.endsWith('.png')){
          let fileBase64 = await FileSystem.readAsStringAsync(result.uri, { encoding: FileSystem.EncodingType.Base64 });      
          setBase64Image(fileBase64);
          setImage(result.uri);
        }
      }

    };


    const onChangeDate = (event, selectedDate) => {
      const currentDate = selectedDate || date;
      setShowDate(Platform.OS === 'ios');
      setBirthdate(currentDate);
    };


    const handleShowDate = function(){
      setBirthdate(new Date('1997-02-26'))
      setShowDate(!showDate);
    }

    return (
      <View style={styles.container}>
         <TouchableOpacity onPress={pickImage}>
          <Image style={styles.avatar} source={{uri: image}}/>
        </TouchableOpacity>  
        <View style={nameView} >
          <TextInput 
            onChangeText={text => setName(text)}
            defaultValue={name} 
            style={styles.inputText}
            placeholder="Name *" 
            placeholderTextColor="#E1E8ED"/>
        </View>
        <View style={surnameView} >
          <TextInput
            onChangeText={text => setSurname(text)}
            defaultValue={surname}   
            style={styles.inputText}
            placeholder="Surname *" 
            placeholderTextColor="#E1E8ED"/>
        </View>
        <TouchableOpacity style={birthDateView} onPress={()=>handleShowDate()}>
            <Text style={styles.dateText}>{ birthDate!='Birth Date *' ? birthDate.getDate()+'-'+(birthDate.getMonth()+1)+'-'+birthDate.getFullYear(): birthDate}</Text>
              {showDate && (
              <DateTimePicker
                testID="dateTimePicker"
                value={birthDate}
                mode={'date'}
                is24Hour={true}
                display="spinner"
                onChange={onChangeDate}
              />
            )}
        </TouchableOpacity>
        

        <View style={styles.languajesContainer} >
            <SignUpLanguajePicker labelTag={'Native*'}    view={nativeView} setSelected={setNative}/>
            <SignUpLanguajePicker labelTag={'Learning*'}  view={leraning1View} setSelected={setLearning1}/>
            <SignUpLanguajePicker labelTag={'Learning*'}  view={leraning2View} setSelected={setLearning2}/>
        </View>

        <Text style={styles.errors}>{errors}</Text>

        {loading ? 
          <View style={styles.btnsContainer}>
            <ActivityIndicator  size="large" color="#fff"/>
          </View> : 
          <TouchableOpacity style={styles.signupBtn} onPress={signUp}>
            <Text style={styles.signupText}>SIGN UP</Text>
          </TouchableOpacity>
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
  },
  errors:{
    color:'red'
  },
  dateText:{
    color:'white'
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

export const langView = {
  backgroundColor:"#465881",
  borderRadius:20,
  height:50,
  width:90,
  justifyContent:"center",
  padding:16,
  margin:5
}

export const langErrorsVies = {
  backgroundColor:"#465881",
  borderRadius:20,
  height:50,
  width:90,
  justifyContent:"center",
  padding:16,
  margin:5,
  borderWidth:1,
  borderColor:'red'
}
