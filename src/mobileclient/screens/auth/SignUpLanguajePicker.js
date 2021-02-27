import React, { useState } from 'react';
import { StyleSheet, TextInput, View,TouchableOpacity } from 'react-native';
import PickerModal from 'react-native-picker-modal-view';
import data from '../discover/languajes.json';

export default function SignUpLanguajePicker(props) {

    const {labelTag} = props;
    const [newLabel,setNewLabel] = useState(labelTag);

    const [selectedLanguaje,setSelectedLanguaje] = useState(data[0])

    const handleSelected = function(selected){
		setSelectedLanguaje(selected);
        if(selected)
          setNewLabel(labelTag.replace('*','')+'\n'+selected.Code)
        return selected;
	}

  return (
    <PickerModal renderSelectView={(disabled, selected, showModal) =>
        <View style={styles.inputView} >
            <TouchableOpacity onPress={showModal}>
              <TextInput  
                style={styles.inputText}
                editable = {false}
                placeholder={newLabel} 
                placeholderTextColor="#E1E8ED"/>
          </TouchableOpacity>
        </View>
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
    languajeItem:{
      borderWidth:1,
      width:90,
      height:40,
      borderColor:'#fff',
      borderRadius:10,
      justifyContent:'center',
    },
    inputView:{
      backgroundColor:"#465881",
      borderRadius:20,
      height:50,
      width:90,
      justifyContent:"center",
      padding:16,
      margin:5
    },
    languajeLabel:{
      textAlign:'center',
      color:"white"
    },
    inputText:{
      height:50,
      color:"white",
      textAlign:'center'
    },
  });