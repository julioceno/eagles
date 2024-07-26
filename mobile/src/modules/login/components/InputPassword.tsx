import React from "react";
import { View, TextInput, TouchableOpacity } from "react-native";
import { Feather } from "@expo/vector-icons";

type InputPasswordProps = {
  value: string;
  onChangeText: (value: string) => void;
  seePassword: boolean;
  setSeePassword: (value: boolean) => void;
  className?: string;
};

export default function InputPassword({
  value,
  onChangeText,
  seePassword,
  setSeePassword,
  className,
}: InputPasswordProps) {
  return (
    <View className={`rounded-xl w-full p-2 flex flex-row justify-between items-center shadow-lg shadow-gray-700 bg-white h-12 ${className}`}>
      <TextInput
        placeholder="Senha"
        value={value}
        onChangeText={onChangeText}
        secureTextEntry={!seePassword}
        className="w-[90%] h-12"
      />
      <TouchableOpacity
        className="flex flex-col justify-center items-center w-12 h-12"
        onPress={() => setSeePassword(!seePassword)}
      >
        <Feather
          name={seePassword ? "eye" : "eye-off"}
          size={18}
          color="black"
        />
      </TouchableOpacity>
    </View>
  );
}
