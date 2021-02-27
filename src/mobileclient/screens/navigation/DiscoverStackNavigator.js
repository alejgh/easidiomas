
import React, { useEffect, useState,useRef } from 'react';
import { StyleSheet, Text, View, TouchableOpacity} from 'react-native';
import { NavigationContainer } from '@react-navigation/native';
import { createStackNavigator } from '@react-navigation/stack';
import Ionicons from 'react-native-vector-icons/Ionicons'
import FiltersScreen from '../discover/FiltersScreen';
import DiscoverScreen from '../DiscoverScreen';
import data from '../discover/languajes.json';


export const DiscoverContext = React.createContext();
export const navigationRef = React.createRef();

export function navigate(name, params) {
    navigationRef.current?.navigate(name, params);
}


export default function DiscoverStackNavigator({navigation}) {

    const [native,setNative] = useState(data[0]);
    const [learning1,setLearning1] = useState(data[1]);
    const [learning2,setLearning2] = useState(data[2]);
    const [minAge,setMinAge] = useState(18);
    const [maxAge,setMaxAge] = useState(40); 

    const Stack = createStackNavigator();

    function FiltersBtn() {
        return (
            <TouchableOpacity onPress={() => navigate('Discover Options')}>
                <Ionicons name={'settings-outline'} size={20} color={'white'} style={styles.configBtn}/>
            </TouchableOpacity>
        );
    }

    function ApplyFiltersBtn() {

        const apply = function(){
            // TODO 
            // NEW SEARCH REQUEST
            //navigation.navigate("Discover");
            navigate('Discover')
        }
        return (
            <TouchableOpacity onPress={() => apply()} style={styles.applyFiltersBtn}>
                <Text style={styles.applyFiltersBtnText}>Apply</Text>
            </TouchableOpacity>
        );
    }


  return (
    <DiscoverContext.Provider value={
        {
            native:native,
            learning1,learning1,
            learning2:learning2,
            minAge:minAge,
            maxAge:maxAge,
            setNative:setNative,
            setLearning1:setLearning1,
            setLearning2:setLearning2,
            setMinAge:setMinAge,
            setMaxAge:setMaxAge}}>
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
                        {props=> <DiscoverScreen {...props} parentNavigation={navigation}/>
                    }
                    </Stack.Screen>
                    <Stack.Screen name="Discover Options" component={FiltersScreen} options={{
                        headerRight: () => (
                            <ApplyFiltersBtn/>
                        )
                    }}/>
                </Stack.Navigator>
            </NavigationContainer>
        </View>
    </DiscoverContext.Provider>
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
    },
    applyFiltersBtn:{
        backgroundColor:'#1DA1F2',
        marginRight:15,
        width:80,
        height:25,
        borderRadius:10

        
    },
    applyFiltersBtnText:{
        textAlign:'center',
        fontSize:18,
        color:'#fff'
    }
  });


