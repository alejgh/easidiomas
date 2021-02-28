import React, { useContext, useState } from 'react';
import { StyleSheet, Text, View,TouchableOpacity, Button} from 'react-native';
import CustomPickerPodal from './CustomPickerModal';
import {DiscoverContext} from '../navigation/DiscoverStackNavigator';
import Ionicons from 'react-native-vector-icons/Ionicons';

export default function FiltersScreen() {

    const {native,setNative,learning1,setLearning1,learning2,setLearning2,minAge,maxAge,setMinAge,setMaxAge} = useContext(DiscoverContext);

    const decrementMinAge = function(){
      if(minAge>0)
        setMinAge(minAge-1);
    }

    const incrementMinAge = function(){
      if(minAge<maxAge)
        setMinAge(minAge+1);
    }

    const decrementMaxAge = function(){
      if(maxAge>minAge)
        setMaxAge(maxAge-1);
    }

    const incrementMaxAge = function(){
        setMaxAge(maxAge+1);
    }


  return (
    <View style={styles.container}>
       <CustomPickerPodal labelTag={"Native"} selectedLanguaje={native} setSelectedLanguaje={setNative}/>
       <CustomPickerPodal labelTag={"Learning"} selectedLanguaje={learning1} setSelectedLanguaje={setLearning1}/>
       <CustomPickerPodal labelTag={"Learning"} selectedLanguaje={learning2} setSelectedLanguaje={setLearning2}/>
       <View style={styles.ageContainer}>
            <View style={styles.ageLabelContainer}>
              <Text style={styles.ageLabel}>MinAge</Text>
            </View>
            <View style={styles.ageLabelContainer}>
              <Text style={styles.ageBoldLabel}>{minAge}</Text>
            </View>
            <View style={styles.ageLabelContainer}>
              <TouchableOpacity onPress={decrementMinAge}>
                <Ionicons name={'remove-circle-outline'} size={40} color={'white'} /> 
              </TouchableOpacity>
              <TouchableOpacity onPress={incrementMinAge}>
                <Ionicons name={'add-circle-outline'} size={40} color={'white'} /> 
              </TouchableOpacity>
            </View>
        </View>
        <View style={styles.ageContainer}>
            <View style={styles.ageLabelContainer}>
              <Text style={styles.ageLabel}>MaxAge</Text>
            </View>
            <View style={styles.ageLabelContainer}>
              <Text style={styles.ageBoldLabel}>{maxAge}</Text>
            </View>
            <View style={styles.ageLabelContainer}>
              <TouchableOpacity onPress={decrementMaxAge}>
                <Ionicons name={'remove-circle-outline'} size={40} color={'white'} /> 
              </TouchableOpacity>
              <TouchableOpacity onPress={incrementMaxAge}>
                <Ionicons name={'add-circle-outline'} size={40} color={'white'} /> 
              </TouchableOpacity>
            </View>
        </View>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#1b2836',
    borderColor:'#fff',
    borderTopWidth:1,
  },
  ageContainer:{
    flex: 0.1,
    backgroundColor: '#1b2836',
    justifyContent:'space-around',
    flexDirection: 'row',
    borderColor:'#fff',
    borderBottomWidth:1,
  },
  ageLabelContainer:{
    width:80,
    alignItems:'center',
    justifyContent:'center',
    flexDirection:'row'
  },
  ageLabel:{
      color:'white',
      fontSize:18,
      textAlign:'center',
  },
  ageBoldLabel:{
    color:'white',
    fontSize:18,
    fontWeight: 'bold',
  },
  ageLabelContainerSpace:{
    backgroundColor:'red',
    width:60
  }
});
