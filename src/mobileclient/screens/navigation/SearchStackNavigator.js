
import React, { useEffect, useState,useRef } from 'react';
import { StyleSheet, Text, View, TouchableOpacity} from 'react-native';
import { NavigationContainer } from '@react-navigation/native';
import { createStackNavigator } from '@react-navigation/stack';
import Ionicons from 'react-native-vector-icons/Ionicons'
import FiltersScreen from '../search/FiltersScreen';
import SearchScreen from '../SearchScreen';
import data from '../search/languajes.json';


export const SearchContext = React.createContext();
export const navigationRef = React.createRef();

export function navigate(name, params) {
    navigationRef.current?.navigate(name, params);
}


export default function SearchStackNavigator({navigation}) {

    const [native,setNative] = useState(data[0]);
    const [learning,setLearning] = useState(data[1]);

    const filter = function(){
        console.log(native)
        console.log(learning)
        // TODO
        // Search Request
      }

    const Stack = createStackNavigator();

    function FiltersBtn() {
        return (
            <TouchableOpacity onPress={() => navigate('Search Options')}>
                <Ionicons name={'settings-outline'} size={20} color={'white'} style={styles.configBtn}/>
            </TouchableOpacity>
        );
    }


    

  return (
    <SearchContext.Provider value={{native:native,setNative:setNative,learning,setLearning,filter:filter}}>
        <View style={styles.container}>
            <NavigationContainer independent  initialRouteName="Discover" ref={navigationRef}>
                <Stack.Navigator screenOptions={{
                    headerStyle: {
                        backgroundColor: '#1b2836',
                        elevation:0,
                        borderBottomWidth:0

                    },
                    headerTintColor: '#fff',
                    headerTitleStyle: {
                        fontWeight: 'bold',
                    }
                }}>                
                    <Stack.Screen name="Discover"options={{
                        headerRight: () => (
                            <FiltersBtn/>
                        )
                    }}>
                        {props=> <SearchScreen {...props} parentNavigation={navigation}/>
                    }
                    </Stack.Screen>
                    <Stack.Screen name="Search Options" component={FiltersScreen}/>
                </Stack.Navigator>
            </NavigationContainer>
        </View>
    </SearchContext.Provider>
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
        display:'none',
        backgroundColor: '#435060',
    },
    configBtn:{
        paddingRight:15
    }
  });


