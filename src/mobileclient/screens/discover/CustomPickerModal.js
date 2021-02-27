import React, { useState } from 'react';
import { StyleSheet, Text, View, TouchableOpacity } from 'react-native';
import PickerModal from 'react-native-picker-modal-view';
import Ionicons from 'react-native-vector-icons/Ionicons';
import data from './languajes.json';
export default function CustomPickerPodal(props) {

    const {labelTag,selectedLanguaje,setSelectedLanguaje} = props;

    const handleSelected = function(selected){
		    setSelectedLanguaje(selected);
        return selected;
	  }

  return (
    <PickerModal renderSelectView={(disabled, selected, showModal) =>
        <TouchableOpacity style={styles.nativeContainer} onPress={showModal}>
            <View style={styles.languajeContainer}>
              <Text style={styles.languajeLabel}>{labelTag}</Text>
            </View>
            <View style={styles.languajeContainer}>
              <Text style={styles.isoLabel}>{selectedLanguaje.Code}</Text>
            </View>
            <View style={styles.languajeContainer}>
              <Text style={styles.languajeLabel}>{selectedLanguaje.Value}</Text>
              <Ionicons name={'chevron-forward-outline'} size={30} color={'white'} style={styles.configBtn}/>
            </View>
        </TouchableOpacity> 
        }
        onSelected={handleSelected}
        items={data}
        sortingLanguage={'tr'}
        showToTopButton={true}
        selected={selectedLanguaje}
        showAlphabeticalIndex={true}
        autoGenerateAlphabeticalIndex={true}
        selectPlaceholderText={'Choose one...'}
        onEndReached={() => console.log('list ended...')}
        searchPlaceholderText={'Search...'}
        requireSelection={false}
        autoSort={false}
        />
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#1b2836',
    borderColor:'#fff',
    borderTopWidth:1,
  },
  nativeContainer:{
    flex: 0.1,
    backgroundColor: '#1b2836',
    justifyContent:'space-around',
    flexDirection: 'row',
    borderColor:'#fff',
    borderBottomWidth:1,
  },
  languajeContainer:{
    width:80,
    alignItems:'center',
    justifyContent:'center',
    flexDirection:'row'
  },
  languajeLabel:{
      color:'white',
      fontSize:18,
  },
  isoLabel:{
    color:'white',
    fontSize:18,
    fontWeight: 'bold'
  }
});
