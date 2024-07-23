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

  const getButtonColor = (type: "button" | "submit" | "reset", disabled?: boolean) => {
    if (disabled) return "gray";
    switch (type) {
      case "submit":
        return "blue";
      case "reset":
        return "red";
      case "button":
      default:
        return "green";
    }
  };

  const buttonColor = getButtonColor(type, disabled);

  return (
    <TouchableOpacity
      onPress={onClick}
      disabled={disabled || loading}
      className={`
        ${buttonColor === "gray" ? "bg-gray-300" : `bg-primary-blue`}
        p-2 rounded-xl flex justify-center items-center w-full h-12 shadow-lg ${
          disabled || loading ? "opacity-60" : "text-primary-blue"}`}
    >
      {loading ? (
        <ActivityIndicator size="small" color="#fff" />
      ) : (
        <Text className="text-white font-bold text-lg">{title}</Text>
      )}
    </TouchableOpacity>
  );
}
