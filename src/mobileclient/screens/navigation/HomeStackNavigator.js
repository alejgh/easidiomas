import React, { useState,useContext } from 'react';
import { Text, StyleSheet,TouchableOpacity} from 'react-native';
import { NavigationContainer } from '@react-navigation/native';
import { createStackNavigator } from '@react-navigation/stack';
import NewPostScreen from '../home/NewPostScreen';
import HomeTabNavigator from './HomeTabNavigator';
import {AppContext} from '../../App';

export const HomeContext = React.createContext();
export const navigationRef = React.createRef();

export function navigate(name, params) {
    navigationRef.current?.navigate(name, params);
}


export default function HomeStackNavigator({navigation}){

    const Stack = createStackNavigator();
    const context = useContext(AppContext);
    const {REQUEST_URI} = context.CONFIG;
  
    const [newPost,setNewPost] = useState(null);

    function PostBtn() {
        const appContext = useContext(AppContext);
        const context = useContext(HomeContext);
        const createPost = async function(){
            console.log(REQUEST_URI+'/posts')
            let response = await fetch(REQUEST_URI+'/posts',{
                method: 'POST',
                headers: {
                  'Accept': 'application/json',
                  'Content-Type': 'application/json',
                  'token':appContext.token
                },
                body: JSON.stringify({
                    content:context.newPost
                })
            });
            console.log(response.status)
            navigate("Home")
        }
        return (
            <TouchableOpacity onPress={() => createPost()} style={styles.postBtn}>
                <Text style={styles.postBtnText}>Post</Text>
            </TouchableOpacity>
        );
    }

    return(
<HomeContext.Provider value={{newPost:newPost,setNewPost:setNewPost}}>
    <NavigationContainer independent  initialRouteName="Home" ref={navigationRef}>
        <Stack.Navigator  screenOptions={{
                headerStyle: {
                    backgroundColor: '#1b2836',
                    borderBottomWidth: 1,
                    borderBottomColor: '#435060',
                    elevation:0,
                    borderBottomWidth:0

                },
                headerTintColor: '#fff',
                headerTitleStyle: {
                    fontWeight: 'bold',
                }
            }}>   
            <Stack.Screen name="Home">
                {props => <HomeTabNavigator {...props} parentNavigation={navigation}/>}
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
</HomeContext.Provider>
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

