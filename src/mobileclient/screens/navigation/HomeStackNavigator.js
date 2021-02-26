import React, { useState,useEffect } from 'react';
import { Text, StyleSheet,TouchableOpacity} from 'react-native';
import { NavigationContainer } from '@react-navigation/native';
import { createStackNavigator } from '@react-navigation/stack';
import HomeScreen from '../HomeScreen';
import NewPostScreen from '../home/NewPostScreen';


export const navigationRef = React.createRef();

export function navigate(name, params) {
    navigationRef.current?.navigate(name, params);
}


export default function HomeStackNavigator({navigation}){

    const Stack = createStackNavigator();

    
    function PostBtn() {
        return (
            <TouchableOpacity onPress={() => navigate('Home')} style={styles.postBtn}>
                <Text style={styles.postBtnText}>Post</Text>
            </TouchableOpacity>
        );
    }

    return(
    <NavigationContainer independent  initialRouteName="Results" ref={navigationRef}>
        <Stack.Navigator  screenOptions={{
                headerStyle: {
                    backgroundColor: '#1b2836',
                    borderBottomWidth: 1,
                    borderBottomColor: '#435060'

                },
                headerTintColor: '#fff',
                headerTitleStyle: {
                    fontWeight: 'bold',
                }
            }}>   
            <Stack.Screen name="Home">
                {props => <HomeScreen {...props} parentNavigation={navigation}/>}
            </Stack.Screen>      
            <Stack.Screen name="New Post"  options={{
                    headerRight: () => (
                        <PostBtn/>
                    )
                }}>
                {props => <NewPostScreen {...props} parentNavigation={navigation}/>}
            </Stack.Screen>
        </Stack.Navigator>
    </NavigationContainer>
    )
}


const styles = StyleSheet.create({
    postBtn:{
        backgroundColor:'#1DA1F2',
        marginRight:15,
        width:80,
        height:25,
        borderRadius:10

        
    },
    postBtnText:{
        textAlign:'center',
        fontSize:18,
        color:'#fff'
    }
  });

