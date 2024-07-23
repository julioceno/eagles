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
  className,
  onChangeText,
}: InputTextProps) {
  return (
    <TextInput
      placeholder={placeholder}
      value={value}
      onChangeText={onChangeText}
      className={`rounded-xl p-2 ${className} shadow-lg bg-white text-lg w-full h-12`}
    />
  );
}
