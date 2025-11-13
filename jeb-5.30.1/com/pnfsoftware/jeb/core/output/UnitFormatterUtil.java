package com.pnfsoftware.jeb.core.output;

import com.pnfsoftware.jeb.util.format.Strings;
import java.util.Collection;

public class UnitFormatterUtil {
   public static void addAllPresentations(IUnitFormatter var0, Collection var1, boolean var2) {
      for (IUnitDocumentPresentation var4 : var1) {
         var0.addPresentation(var4, var2);
      }
   }

   public static IUnitDocumentPresentation getPresentationByIdentifier(IUnitFormatter var0, long var1) {
      for (IUnitDocumentPresentation var4 : var0.getPresentations()) {
         if (var4.getId() == var1) {
            return var4;
         }
      }

      return null;
   }

   public static IUnitDocumentPresentation getPresentationByName(IUnitFormatter var0, String var1) {
      for (IUnitDocumentPresentation var3 : var0.getPresentations()) {
         if (Strings.equals(var3.getLabel(), var1)) {
            return var3;
         }
      }

      return null;
   }

   public static IUnitDocumentPresentation getPresentationByDocument(IUnitFormatter var0, IGenericDocument var1) {
      for (IUnitDocumentPresentation var3 : var0.getPresentations()) {
         if (var3.getDocument() == var1) {
            return var3;
         }
      }

      return null;
   }
}
