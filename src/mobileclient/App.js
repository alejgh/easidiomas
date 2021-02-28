import  React, {useEffect, useState} from 'react';
import { NavigationContainer } from '@react-navigation/native';
import TabMenuNavigator from './screens/navigation/AppTabNavigator';
import {  StyleSheet } from 'react-native';
import AuthStackNavigator from './screens/navigation/AuthStackNavigator';

export const AppContext = React.createContext();

export default function App() {

  const [user,setUser] = useState(null); //Poner a null
  const [token,setToken] = useState(null); 

  return (
    <AppContext.Provider value={{user:user,setUser:setUser,token:token,setToken:setToken,CONFIG:RELEASE_CONFIG,TOKEN:TOKEN}}>
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
    avatar: 'https://bootdey.com/img/Content/avatar/avatar2.png'
  }


  export const TOKEN = 'TCxSdlWlv8ilhgpBNvigu//VFXPY+r4TYnF+IIPJ+3s9wTAapV/RUDI0E5cBmIjIJrfdvp4Wo+K/59VUATUz/rgruurZOat+ZcuC8PG12XhV0nAKYC2a9VdDH/vb/Jd5rz9FrDtGuZ7rszVg9S/hb9PX0ACa2FXXNWh3NO6ev02a+P1WJPCK2UfRcqtd5eEs7qHfb5YpvillJRoHGMIJ8QGJdPPxyovGOAMKD9uwnPvaWPTPnj5A0FDsSYp79O4Nbz8snSM1K2VvC3dryKJjVy8PYdI22J2NCJ9PymWaHyQLKXMLbvkccxtCt5+Z+wSO8NTMAK8kaoUaS4WWS2gMf0F1cGJqRakvz4sQn0RuP3c=';
    