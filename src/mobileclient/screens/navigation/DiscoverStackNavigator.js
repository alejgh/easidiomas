
import React, { useEffect, useState,useRef, useContext } from 'react';
import { StyleSheet, Text, View, TouchableOpacity} from 'react-native';
import { NavigationContainer } from '@react-navigation/native';
import { createStackNavigator } from '@react-navigation/stack';
import Ionicons from 'react-native-vector-icons/Ionicons'
import FiltersScreen from '../discover/FiltersScreen';
import DiscoverScreen from '../DiscoverScreen';
import data from '../discover/languajes.json';

export const navigationRef = React.createRef();

export function navigate(name, params) {
    navigationRef.current?.navigate(name, params);
}


export default function DiscoverStackNavigator({navigation}) {

    const [native,setNative] = useState('');
    const [learning1,setLearning1] = useState('');
    const [learning2,setLearning2] = useState('');
    const [minAge,setMinAge] = useState(0);
    const [maxAge,setMaxAge] = useState(100);


    const Stack = createStackNavigator();



    function ApplyFiltersBtn(props) {
        const apply = function(){
            navigate('Discover',{filters:getFilters()})
        }
        return (
            <TouchableOpacity onPress={() => apply()} style={styles.applyFiltersBtn}>
                <Text style={styles.applyFiltersBtnText}>Apply</Text>
            </TouchableOpacity>
        );
    }


    const getFilters = function(){
        let nativeFilter = native?.Code?.toUpperCase();
        let learning1Filter = learning1?.Code?.toUpperCase();
        let learning2Filter = learning2?.Code?.toUpperCase();
        if(!nativeFilter)
            nativeFilter = '';
        if(!learning1Filter)
            learning1Filter = '';
        if(!learning2Filter)
            learning2Filter = '';

        //TODO 
        //AGEEEEE

        return '?speaks='+nativeFilter+'&wantsToLearn='+learning1Filter+','+learning2Filter;
    }


  return (
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
                    <Stack.Screen name="Discover">
                        {props=> <DiscoverScreen {...props} parentNavigation={navigation} 
                        native={native} 
                        setNative={setNative}
                        learning1={learning1}
                        setLearning1={setLearning1}
                        learning2={learning2}
                        setLearning2={setLearning2}
                        minAge={minAge}
                        maxAge={maxAge}
                        setMinAge={setMinAge}
                        setMaxAge={setMaxAge}
                        getFilters={getFilters}/>
                    }
                    </Stack.Screen>
                    <Stack.Screen name="Discover Options"  options={{
                        headerRight: () => (
                            <ApplyFiltersBtn  />
                        )
                    }}>
                          {props=> <FiltersScreen {...props}  
                                            native={native} 
                                            setNative={setNative}
                                            learning1={learning1}
                                            setLearning1={setLearning1}
                                            learning2={learning2}
                                            setLearning2={setLearning2}
                                            minAge={minAge}
                                            maxAge={maxAge}
                                            setMinAge={setMinAge}
                                            setMaxAge={setMaxAge} />
                    }
                    </Stack.Screen>
                </Stack.Navigator>
            </NavigationContainer>
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


