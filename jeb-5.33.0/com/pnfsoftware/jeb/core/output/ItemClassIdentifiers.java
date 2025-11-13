package com.pnfsoftware.jeb.core.output;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerDeprecatedEnumField;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;

@Ser
public enum ItemClassIdentifiers {
   DEFAULT(0),
   ARTIFACT(1),
   INFO_USELESS(256),
   INFO_DEPRECATED(257),
   INFO_DEBUG(258),
   INFO_NORMAL(259),
   INFO_WARNING(260),
   INFO_ERROR(261),
   INFO_DANGEROUS(262),
   INFO_MALFORMED(263),
   INFO_CORRUPT(264),
   TYPE_BYTE(130),
   TYPE_SHORT(131),
   TYPE_INTEGER(132),
   TYPE_LONG(133),
   TYPE_FLOAT(134),
   TYPE_DOUBLE(135),
   COMMENT(16),
   ADDRESS(17),
   BYTECODE(18),
   DIRECTIVE(19),
   KEYWORD(20),
   MNEMONIC(21),
   OPCODE(22),
   LABEL(23),
   PARAMETER(24),
   IDENTIFIER(25),
   NUMBER(26),
   CHARACTER(27),
   STRING(28),
   CLASS_NAME(29),
   FIELD_NAME(30),
   METHOD_NAME(31),
   EXTERNAL_CLASS_NAME(32),
   EXTERNAL_FIELD_NAME(33),
   EXTERNAL_METHOD_NAME(34),
   PACKAGE_NAME(35),
   REGISTER(36),
   IMMEDIATE(37),
   MNEMONIC_PREFIX(39),
   ADDRESS_SLACK(40),
   LABEL_OOR(41),
   LABEL_ALTERNATE(44),
   ANNOTATION(45),
   LAMBDA(46),
   SEPARATOR(47),
   CODE_UNKNOWN(48),
   CODE_INSTRUCTIONS(49),
   CODE_ROUTINE(50),
   CODE_DATA(51),
   CODE_LIBRARY(52),
   CODE_METADATA(53),
   CODE_SLACK(54),
   CODE_STUB(55),
   MARKUP_ELEMENT(64),
   MARKUP_ATTRIBUTE_NAME(65),
   MARKUP_ATTRIBUTE_VALUE(66),
   MARKUP_TEXT(67),
   MARKUP_COMMENT(68),
   MARKUP_ACTIVE(69),
   MARKUP_OTHER(70),
   TYPE_PROTOTYPE(80),
   RESULT_SUCCESS(272),
   RESULT_ERROR(273),
   @SerDeprecatedEnumField
   DEBUGGING_PC_LINE(512),
   STRING_GENERATED(38),
   FIELD_NAME_GENERATED(56),
   METHOD_NAME_GENERATED(57),
   COMMENT_AUTO(42),
   CODE_ORPHAN(58),
   CODE_ERRORS(59),
   NUMBER_GENERATED(60),
   TYPE(129);

   private final int id;

   private ItemClassIdentifiers(int var3) {
      this.id = var3;
   }

   public int getId() {
      return this.id;
   }

   public static ItemClassIdentifiers getById(int var0) {
      for (ItemClassIdentifiers var4 : values()) {
         if (var4.id == var0) {
            return var4;
         }
      }

      return null;
   }

   public static ItemClassIdentifiers[] currentValues() {
      ArrayList var0 = new ArrayList(Arrays.asList(values()));

      for (Field var4 : ItemClassIdentifiers.class.getFields()) {
         if (var4.getType() == ItemClassIdentifiers.class && (var4.getModifiers() & 8) != 0 && var4.getAnnotation(SerDeprecatedEnumField.class) != null) {
            try {
               ItemClassIdentifiers var5 = (ItemClassIdentifiers)var4.get(null);
               var0.remove(var5);
            } catch (ReflectiveOperationException var6) {
            }
         }
      }

      return (ItemClassIdentifiers[])var0.toArray(new ItemClassIdentifiers[var0.size()]);
   }
}
