import { count } from '@/stores/timer'
import '@/styles/global.css'

import { Slot } from 'expo-router'
import { StatusBar, Text, TouchableOpacity, View } from 'react-native'

export default function Layout() {

  return (
    <View className="flex-1">
      <StatusBar 
        barStyle="dark-content" 
        backgroundColor="transparent" 
        translucent
      />
      <Slot />
      <TouchableOpacity
        onPress={() => count.increase()}
      >
        <Text>aaa</Text>
        <Text>aaa</Text>
        <Text>aaa</Text>
        <Text>aaa</Text>
      </TouchableOpacity>
    </View>
  )
}