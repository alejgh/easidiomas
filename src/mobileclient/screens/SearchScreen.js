
import React, { useEffect, useState } from 'react';
import { StyleSheet, Text, View, FlatList,BackHandler,Keyboard } from 'react-native';
import { SearchBar } from 'react-native-elements';
import SearchTabNavigator from './navigation/SearchTabNavigator';

export default function SearchScreen({navigation}) {

    const [search,setSearch] = useState('')

    const updateSearch = (search) => {
       setSearch(search)
    };

    const handleFocus = function(){
        navigation.setOptions({
            tabBarVisible: false
        });
    }

    const handleCancel = function(){
        navigation.setOptions({
            tabBarVisible: true
        });
    }
    

    useEffect(()=>{
       Keyboard.addListener('keyboardDidShow', handleFocus); 
       Keyboard.addListener('keyboardDidHide', handleCancel);
        return ()=>{
            Keyboard.removeListener('keyboardDidShow', handleFocus); 
            Keyboard.removeListener('keyboardDidHide', handleCancel);
        }
    },[])

  return (
    <View style={styles.container}>
        <SearchBar
                placeholder="Search Easidiomas"
                onChangeText={updateSearch}
                value={search}
                containerStyle={styles.searchContainer}
                inputContainerStyle={styles.inputContainerStyle}
                leftIconContainerStyle={styles.leftIconContainerStyle}
                style={styles.search}

            />
     
        <SearchTabNavigator/>

    </View>
  );
}



const styles = StyleSheet.create({
    container:{
        flex:1,
        backgroundColor: '#1b2836'
    },
    searchContainer: {
        paddingTop:30,
        backgroundColor: '#1b2836'
    },
    inputContainerStyle:{
        backgroundColor: '#1b2836'
    },
    search: {
        borderRadius:50,
        backgroundColor: '#435060',
        paddingLeft:15
    },
    leftIconContainerStyle:{
        display:"none",
        backgroundColor: '#435060',
    }
  });


