import  React, {useEffect, useState} from 'react';
import { NavigationContainer } from '@react-navigation/native';
import TabMenuNavigator from './screens/navigation/AppTabNavigator';
import {  StyleSheet } from 'react-native';
import AuthStackNavigator from './screens/navigation/AuthStackNavigator';

export const AppContext = React.createContext();

export default function App() {

  console.disableYellowBox = true;
  //LogBox.ignoreAllLogs(true)

  const [user,setUser] = useState(null); //Poner a null
  const [token,setToken] = useState(null); 

  return (
    <AppContext.Provider value={{user:user,setUser:setUser,token:token,setToken:setToken,CONFIG:RELEASE_CONFIG}}>
        <NavigationContainer  initialRouteName="Login">
            {user ? <TabMenuNavigator/>: <AuthStackNavigator/>}
      </NavigationContainer>
    </AppContext.Provider>
  );
}
const styles = StyleSheet.create({
  spinnerTextStyle: {
    color: '#FFF'
  }})



  export const DEBUG_CONFIG = {
    REQUEST_URI:'http://localhost:5000/api/mock',
  }
  


  export const RELEASE_CONFIG = {
    REQUEST_URI:'http://156.35.82.22:5000/api',
  }


  export const DefaultUser = {
    id: 99,
    name: 'Pablo',
    surname: 'Menéndez Suárez',
    username: '@mistermboy',
    learning: ['en', 'cn'],
    speaks: 'es',
    birthDate: '<long_time_since_epoch>',
    avatar: 'https://www.bootdey.com/img/Content/avatar/avatar2.png'
  }
