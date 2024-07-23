import "@/styles/global.css";
import { Slot } from "expo-router";
import { StatusBar, Text, TouchableOpacity, View } from "react-native";

export default function Layout() {


  return (
    <View className="flex-1">
      <StatusBar
        barStyle="dark-content"
        backgroundColor="transparent"
        translucent
      />
      <Slot />
    </View>
  );
}

