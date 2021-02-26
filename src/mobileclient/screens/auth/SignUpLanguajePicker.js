import React, { useState } from 'react';
import { StyleSheet, Text, View, TouchableOpacity } from 'react-native';
import PickerModal from 'react-native-picker-modal-view';
import data from '../search/languajes.json';

export default function SignUpLanguajePicker(props) {

    const {labelTag} = props;
    const [newLabel,setNewLabel] = useState(labelTag);

    const [selectedLanguaje,setSelectedLanguaje] = useState(data[0])

    const handleSelected = function(selected){
		setSelectedLanguaje(selected);
        setNewLabel(labelTag+'-'+selected.Code)
        return selected;
	}

  return (
    <PickerModal renderSelectView={(disabled, selected, showModal) =>
                <TouchableOpacity style={styles.languajeItem} onPress={showModal}>
                <Text style={styles.languajeLabel}>{newLabel}</Text>
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
    languajeItem:{
      borderWidth:1,
      width:90,
      height:40,
      borderColor:'#fff',
      borderRadius:10,
      justifyContent:'center',
  
    },
    languajeLabel:{
      textAlign:'center',
      color:"white"
    }
  });