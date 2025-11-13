package com.pnfsoftware.jeb.core.output.tree.impl;

import com.pnfsoftware.jeb.core.output.tree.INode;
import com.pnfsoftware.jeb.core.output.tree.INodeCoordinates;
import com.pnfsoftware.jeb.core.output.tree.ITreeDocument;
import com.pnfsoftware.jeb.core.properties.IPropertyManager;
import com.pnfsoftware.jeb.util.events.EventSource;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Ser
public abstract class AbstractTreeDocument extends EventSource implements ITreeDocument {
   @Override
   public void dispose() {
   }

   @Override
   public IPropertyManager getPropertyManager() {
      return null;
   }

   @Override
   public List getColumnLabels() {
      return null;
   }

   @Override
   public int getInitialExpansionLevel() {
      return 0;
   }

   @Override
   public String coordinatesToAddress(INodeCoordinates var1) {
      return var1 == null ? null : formatIntegerList(var1.getPath());
   }

   @Override
   public INodeCoordinates addressToCoordinates(String var1) {
      if (var1 == null) {
         return null;
      } else {
         List var2 = parseIntegerList(var1);
         return var2 == null ? null : new NodeCoordinates(var2);
      }
   }

   static String formatIntegerList(List var0) {
      StringBuilder var1 = new StringBuilder();
      int var2 = 0;

      for (int var4 : var0) {
         if (var2 >= 1) {
            var1.append(',');
         }

         var1.append(var4);
         var2++;
      }

      return var1.toString();
   }

   static List parseIntegerList(String var0) {
      ArrayList var1 = new ArrayList();

      for (String var5 : var0.split(",")) {
         try {
            int var6 = Integer.parseInt(var5.trim());
            var1.add(var6);
         } catch (NumberFormatException var7) {
            return null;
         }
      }

      return var1;
   }

   public String coordinatesToAddress(INodeCoordinates var1, Function var2) {
      if (var1 != null && var1.getPath() != null && !var1.getPath().isEmpty()) {
         ArrayList var3 = new ArrayList();
         INode var4 = (INode)this.getRoots().get(0);
         var3.add(var4);
         if (var4 == null) {
            return (String)var2.apply(var3);
         } else {
            for (int var5 = 1; var5 < var1.getPath().size(); var5++) {
               int var6 = (Integer)var1.getPath().get(var5);
               if (var6 < 0 || var6 >= var4.getChildren().size()) {
                  return (String)var2.apply(var3);
               }

               var4 = (INode)var4.getChildren().get(var6);
               var3.add(var4);
               if (var4 == null) {
                  return (String)var2.apply(var3);
               }
            }

            return (String)var2.apply(var3);
         }
      } else {
         return null;
      }
   }
}
