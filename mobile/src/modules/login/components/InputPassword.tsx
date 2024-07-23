import React from "react";
import { View, TextInput, TouchableOpacity } from "react-native";
import { Feather } from "@expo/vector-icons";

type InputPasswordProps = {
  password: string;
  setPassword: (value: string) => void;
  seePassword: boolean;
  setSeePassword: (value: boolean) => void;
};

export default function InputPassword({
  password,
  setPassword,
  seePassword,
  setSeePassword,
}: InputPasswordProps) {
  return (
    <View className="rounded-xl w-full p-2 flex flex-row justify-between items-center shadow-lg bg-white h-12">
      <TextInput
        placeholder="Senha"
        value={password}
        onChangeText={setPassword}
        secureTextEntry={!seePassword}
        className="w-[95%] h-full text-lg"
      />
      <TouchableOpacity
        className="w-6 h-6"
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
