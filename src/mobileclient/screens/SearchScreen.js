
import React, { useEffect, useState } from 'react';
import { StyleSheet, Text, View, FlatList,BackHandler,Keyboard } from 'react-native';
import { SearchBar } from 'react-native-elements';


export default function SearchScreen({navigation}) {

    const [search,setSearch] = useState('')

    const updateSearch = (search) => {
       setSearch(search)
    };

    const handleFocus = function(){
        console.log('focus')
        navigation.setOptions({
            tabBarVisible: false
        });
    }

    const handleCancel = function(){
        console.log('cancel')
        navigation.setOptions({
            tabBarVisible: true
        });
    }
    


    useEffect(()=>{
       // BackHandler.addEventListener('hardwareBackPress', handleCancel);
       Keyboard.addListener('keyboardDidShow', handleFocus); 
       Keyboard.addListener('keyboardDidHide', handleCancel);
        return ()=>{
            //BackHandler.removeEventListener('hardwareBackPress', handleCancel);
            Keyboard.removeListener('keyboardDidShow', handleFocus); 
            Keyboard.removeListener('keyboardDidHide', handleCancel);
        }
    },[])

  return (
    <View>
        <SearchBar
                placeholder="Type Here..."
                onChangeText={updateSearch}
                cancelButtonProps={
                    {disabled:true}
                }
                value={search}
                containerStyle={styles.searchContainer}
                style={styles.search}
            />

    </View>
  );
}



const styles = StyleSheet.create({
    searchContainer: {
        paddingTop:30,
        backgroundColor: '#1b2836'
    },
    search: {
        backgroundColor: '#1b2836'
    },
  });


