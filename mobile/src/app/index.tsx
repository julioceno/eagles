import React, { useState } from "react";
import { View, Text, TouchableOpacity, StyleSheet } from "react-native";
import { Button, InputText, InputPassword } from "@/modules/login/components";
import { useForm, SubmitHandler, Controller } from "react-hook-form";
import { z } from "zod";
import { zodResolver } from "@hookform/resolvers/zod/";

const schema = z.object({
  email: z
    .string({
      message: "O e-mail é obrigatório",
    })
    .email({
      message: "O e-mail deve ser um e-mail válido",
    })
    .toLowerCase(),
  password: z.string({
    message: "A senha é obrigatória",
  }).min(6, "A senha deve conter no mínimo 6 caracteres"),
});

type FormValues = z.infer<typeof schema>;

export default function Index() {
  const [isSubmitting, setIsSubmitting] = useState(false);
  const [seePassword, setSeePassword] = useState(false);

  const {
    control,
    handleSubmit,
    formState: { errors, isValid, isLoading },
    watch,
  } = useForm<FormValues>({
    resolver: zodResolver(schema),
  });

  const onSubmit: SubmitHandler<FormValues> = (data) => {
    setIsSubmitting(true);
    setTimeout(() => {
      setIsSubmitting(false);
    }, 3000);

    console.log(data);
  };

  return (
    <View className="flex-1 justify-between items-center p-5">
      <View className="flex-col justify-center items-center w-full gap-4 flex-1">
        <View className="flex flex-col justify-center items-start w-full gap-6 my-5">
          <Text className="font-bold text-5xl" style={styles.textShadow}>
            Bem vindo de volta!
          </Text>
          <Text
            className="font-semibold text-xl text-gray-500"
            style={styles.textShadow}
          >
            Entre com seu usuário e sua senha
          </Text>
        </View>
        <View className="flex flex-col justify-center items-center w-full gap-4">
          <Controller
            name="email"
            control={control}
            render={({ field: { onChange, value } }) => (
              <InputText
                placeholder="E-mail"
                value={value}
                onChangeText={onChange}
                className="rounded-xl p-2 shadow-lg shadow-gray-700 bg-white text-sm w-full h-12"
              />
            )}
          />
          {errors.email && (
            <Text style={styles.errorText}>{errors.email.message}</Text>
          )}
          <Controller
            name="password"
            control={control}
            render={({ field: { onChange, value } }) => (
              <InputPassword
                seePassword={seePassword}
                setSeePassword={setSeePassword}
                value={value}
                onChangeText={onChange}
                className="rounded-xl p-2 shadow-lg shadow-gray-700 bg-white text-sm w-full h-12"
              />
            )}
          />
          {errors.password && (
            <Text style={styles.errorText}>{errors.password.message}</Text>
          )}
          <Button
            title="Login"
            disabled={!isValid}
            loading={isLoading || isSubmitting}
            type="button"
            onClick={handleSubmit(onSubmit)}
          />
          <TouchableOpacity className="w-full">
            <Text
              className="text-xs font-semibold text-primary-blue"
              style={styles.textShadow}
            >
              Esqueceu sua senha?
            </Text>
          </TouchableOpacity>
        </View>
      </View>
      <View className="flex-row mb-20">
        <Text
          className="text-base font-semibold text-gray-500 drop-shadow-lg"
          style={styles.textShadow}
        >
          Não tem uma conta?{" "}
        </Text>
        <TouchableOpacity>
          <Text
            className="text-base font-semibold text-primary-blue drop-shadow-lg"
            style={styles.textShadow}
          >
            Cadastre-se
          </Text>
        </TouchableOpacity>
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  textShadow: {
    textShadowColor: "rgba(0, 0, 0, 0.3)",
    textShadowOffset: { width: 0, height: 2 },
    textShadowRadius: 10,
  },
  errorText: {
    color: "red",
    fontSize: 12,
    padding: 0,
    margin: 0,
    height: 12,
  },
});
