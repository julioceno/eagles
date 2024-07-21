import { count } from '@/stores/timer'
import { Observer } from "mobx-react-lite"
import { Text, View } from 'react-native'



export default function Index() {

  return (
    <View className='px-10 pt-32'>
      <View className='flex gap-2'>
        <Text className='font-bold text-5xl'>Bem vindo de volta!</Text>
        <Text className='font-semibold text-xl text-gray-500'>Ente com seu usuário e sua senha</Text>
      </View>
      <Observer>
       { () => (
        <Text>{count.count}</Text>
       )
       }
      </Observer>
 
    </View>
  )
}