import React, { useState } from "react";
import { View, Text, TouchableOpacity } from "react-native";
import { Button, InputText, InputPassword } from "@/modules/login/components";

export default function Index() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [isSubmitting, setIsSubmitting] = useState(false);
  const [seePassword, setSeePassword] = useState(false);

  const isFormValid = username !== "" && password !== "";

  const handleLogin = () => {
    if (isFormValid) {
      setIsSubmitting(true);
      setTimeout(() => {
        setIsSubmitting(false);
        // Lógica de login aqui
      }, 2000);
    }
  };

  return (
    <View className="flex-1 justify-between items-center p-5">
      <View className="flex-col justify-center items-center w-full gap-4 flex-1">
        <View className="flex flex-col justify-center items-start w-full gap-6 my-5">
          <Text className="font-bold text-5xl">Bem vindo de volta!</Text>
          <Text className="font-semibold text-xl text-gray-500">
            Entre com seu usuário e sua senha
          </Text>
        </View>
        <View className="flex flex-col justify-center items-center w-full gap-4">
          <InputText
            placeholder="Usuário"
            value={username}
            onChangeText={setUsername}
          />
          <InputPassword
            password={password}
            setPassword={setPassword}
            seePassword={seePassword}
            setSeePassword={setSeePassword}
          />
          <Button
            title="Login"
            onClick={handleLogin}
            disabled={!isFormValid}
            loading={isSubmitting}
            type="submit"
          />
          <TouchableOpacity className=" w-full">
            <Text className="text-base font-semibold text-primary-blue">
              Esqueceu sua senha?
            </Text>
          </TouchableOpacity>
        </View>
      </View>
      <View className="flex-row mb-20">
        <Text className="text-base font-semibold text-gray-500 drop-shadow-lg	">
          Não tem uma conta?{" "}
        </Text>
        <TouchableOpacity>
          <Text className="text-base font-semibold text-primary-blue drop-shadow-lg	">
            Cadastre-se
          </Text>
        </TouchableOpacity>
      </View>
    </View>
  );
}
