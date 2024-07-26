import React from "react";
import { TextInput } from "react-native";

type InputTextProps = {
  placeholder: string;
  value: string;
  onChangeText: (value: string) => void;
  className?: string;
};

export default function InputText({
  placeholder,
  value,
  onChangeText,
  className,
}: InputTextProps) {
  return (
    <TextInput
      placeholder={placeholder}
      value={value}
      onChangeText={onChangeText}
      className={`rounded-xl p-2 ${className} shadow-lg shadow-gray-700 bg-white text-sm w-full h-12`}
    />
  );
}
