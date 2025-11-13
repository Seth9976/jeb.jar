package com.pnfsoftware.jeb.core.output.code.coordinates;

public class CodeCoordinatesUtil {
   public static Integer getBaseObjectIndex(ICodeCoordinates var0) {
      if (var0 instanceof ClassCoordinates) {
         return ((ClassCoordinates)var0).getClassId();
      } else if (var0 instanceof FieldCoordinates) {
         return ((FieldCoordinates)var0).getFieldId();
      } else if (var0 instanceof MethodCoordinates) {
         return ((MethodCoordinates)var0).getMethodId();
      } else {
         return var0 instanceof InstructionCoordinates ? ((InstructionCoordinates)var0).getMethodId() : null;
      }
   }

   public static Integer getMethodObjectIndex(ICodeCoordinates var0) {
      if (var0 instanceof MethodCoordinates) {
         return ((MethodCoordinates)var0).getMethodId();
      } else {
         return var0 instanceof InstructionCoordinates ? ((InstructionCoordinates)var0).getMethodId() : null;
      }
   }

   public static int distance(ICodeCoordinates var0, ICodeCoordinates var1) {
      if (var0 instanceof MethodCoordinates) {
         var0 = new InstructionCoordinates(((MethodCoordinates)var0).getMethodId(), 0);
      }

      if (var1 instanceof MethodCoordinates) {
         var1 = new InstructionCoordinates(((MethodCoordinates)var1).getMethodId(), 0);
      }

      if (var0 instanceof InstructionCoordinates && var1 instanceof InstructionCoordinates) {
         InstructionCoordinates var2 = (InstructionCoordinates)var0;
         InstructionCoordinates var3 = (InstructionCoordinates)var1;
         if (var2.getMethodId() == var3.getMethodId()) {
            return Math.abs(var2.getOffset() - var3.getOffset());
         }
      }

      return Integer.MAX_VALUE;
   }

   public static ICodeCoordinates getMostAccurate(ICodeCoordinates var0, ICodeCoordinates var1) {
      if (var0 instanceof MethodCoordinates) {
         var0 = new InstructionCoordinates(((MethodCoordinates)var0).getMethodId(), 0);
      }

      if (var1 instanceof MethodCoordinates) {
         var1 = new InstructionCoordinates(((MethodCoordinates)var1).getMethodId(), 0);
      }

      if (var0 instanceof InstructionCoordinates && var1 instanceof InstructionCoordinates) {
         InstructionCoordinates var2 = (InstructionCoordinates)var0;
         InstructionCoordinates var3 = (InstructionCoordinates)var1;
         if (var2.getOffset() < 0 && var3.getOffset() >= 0) {
            return var3;
         }
      }

      return (ICodeCoordinates)var0;
   }
}
