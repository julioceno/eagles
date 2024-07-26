import React from "react";
import { ActivityIndicator, Text, TouchableOpacity } from "react-native";

interface ButtonProps {
  type: "button" | "submit" | "reset";
  title: string;
  onClick: () => void;
  disabled?: boolean;
  loading?: boolean;
}

export default function Button({
  type,
  title,
  onClick,
  disabled = false,
  loading = false,
}: ButtonProps) {
  return (
    <TouchableOpacity
      onPress={onClick}
      disabled={disabled || loading}
      className={`bg-gray-300  p-2 rounded-xl flex justify-center items-center w-full h-12 shadow-lg shadow-gray-700 ${
        disabled || loading ? "opacity-60" : "text-primary-blue"
      } ${disabled ? "bg-gray-300" : "bg-primary-blue"}`}
    >
      {loading ? (
        <ActivityIndicator size="small" color="#fff" />
      ) : (
        <Text className="text-white font-bold text-sm">{title}</Text>
      )}
    </TouchableOpacity>
  );
}
