import React, { useState } from 'react';
import { StyleSheet, Text, View, TouchableOpacity } from 'react-native';
import PickerModal from 'react-native-picker-modal-view';
import data from './languajes.json';
import Ionicons from 'react-native-vector-icons/Ionicons';

export default function CustomPickerPodal(props) {

    const {labelTag} = props;

    const [selectedLanguaje,setSelectedLanguaje] = useState(data[0])

    const handleSelected = function(selected){
		setSelectedLanguaje(selected);
        return selected;
	}

  return (
    <PickerModal renderSelectView={(disabled, selected, showModal) =>
        <TouchableOpacity style={styles.nativeContainer} onPress={showModal}>
            <View style={styles.nativeLabelContainer}>
              <Text style={styles.nativeLabel}>{labelTag}</Text>
            </View>
            <View style={styles.nativeLabelContainer}>
              <Text style={styles.isoLabel}>{selectedLanguaje.Code}</Text>
            </View>
            <View style={styles.nativeLanguajeContainer}>
              <Text style={styles.nativeLabel}>{selectedLanguaje.Value}</Text>
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
  nativeLabelContainer:{
    flex: 0.3,
    justifyContent:'center',
    alignItems:'center',
    textAlign:'left',
  },
  nativeLanguajeContainer:{
    alignItems:'center',
    flexDirection:'row'
  },
  nativeLabel:{
      color:'white',
      fontSize:18,
  },
  isoLabel:{
    color:'white',
    fontSize:18,
    fontWeight: 'bold'
  },
  ageContainer:{
    flex: 0.2,
    backgroundColor: '#1b2836',
    justifyContent:'center',
    alignItems: 'center',
    flexDirection: 'column',
    borderColor:'#fff',
    borderBottomWidth:1,
  },
  ageLabel:{
    color:'white',
    fontSize:18,
    marginTop:10
  }
});
