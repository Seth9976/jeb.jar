package com.pnfsoftware.jeb.core.units;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.List;

@Ser
public class MetadataManager implements IMetadataManager {
   @SerId(1)
   private List groups = new ArrayList();

   @Override
   public List getGroups() {
      return this.groups;
   }

   @Override
   public int getGroupCount() {
      return this.getGroups().size();
   }

   @Override
   public IMetadataGroup getGroupByName(String var1) {
      for (IMetadataGroup var3 : this.getGroups()) {
         if (Strings.equals(var3.getName(), var1)) {
            return var3;
         }
      }

      return null;
   }

   @Override
   public boolean addGroup(IMetadataGroup var1) {
      return this.insertGroup(this.getGroupCount(), var1);
   }

   @Override
   public boolean insertGroup(int var1, IMetadataGroup var2) {
      if (var1 >= 0 && var1 <= this.getGroupCount()) {
         String var3 = var2.getName();
         if (var3 != null && !var3.isEmpty() && this.getGroupByName(var3) == null) {
            this.getGroups().add(var1, var2);
            return true;
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   @Override
   public boolean removeGroup(int var1) {
      if (var1 >= 0 && var1 < this.getGroupCount()) {
         this.getGroups().remove(var1);
         return true;
      } else {
         return false;
      }
   }

   @Override
   public boolean removeGroupByName(String var1) {
      int var2 = 0;

      for (IMetadataGroup var4 : this.getGroups()) {
         if (Strings.equals(var4.getName(), var1)) {
            break;
         }

         var2++;
      }

      if (var2 >= this.getGroups().size()) {
         return false;
      } else {
         this.getGroups().remove(var2);
         return true;
      }
   }
}
