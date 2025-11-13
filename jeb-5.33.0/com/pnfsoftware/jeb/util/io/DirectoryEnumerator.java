package com.pnfsoftware.jeb.util.io;

import com.pnfsoftware.jeb.util.logging.GlobalLog;
import java.io.File;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class DirectoryEnumerator implements Iterable {
   private File base;
   private Pattern pattern;
   private boolean recurse;
   private Set blacklistedDirnames = new HashSet();

   public DirectoryEnumerator(File var1, String var2, boolean var3) {
      this(var1);
      if (var2 != null) {
         this.setFilterString(var2, false, false);
      }

      this.setRecurse(var3);
   }

   public DirectoryEnumerator(File var1) {
      if (!var1.isDirectory()) {
         throw new IllegalArgumentException("The base is not a directory: " + var1);
      } else {
         this.base = var1;
      }
   }

   public void setFilterString(String var1, boolean var2, boolean var3) {
      try {
         short var4 = 0;
         if (var2) {
            var4 |= 2;
         }

         if (var3) {
            var4 |= 256;
         }

         if (!var1.startsWith("*")) {
            var1 = "^" + var1;
         }

         if (!var1.endsWith("*")) {
            var1 = var1 + "$";
         }

         var1 = var1.replace(".", "\\.").replace("*", ".*").replace("?", ".");
         this.pattern = Pattern.compile(var1, var4);
      } catch (PatternSyntaxException var5) {
         throw new IllegalArgumentException("The filter string could not be converted to a regex pattern", var5);
      }
   }

   public void setFilter(Pattern var1) {
      this.pattern = var1;
   }

   public Pattern getFilter() {
      return this.pattern;
   }

   public void setRecurse(boolean var1) {
      this.recurse = var1;
   }

   public boolean isRecurse() {
      return this.recurse;
   }

   public void addBlackListedDirectory(String var1) {
      this.blacklistedDirnames.add(var1);
   }

   public Collection getBlacklistedDirnames() {
      return Collections.unmodifiableCollection(this.blacklistedDirnames);
   }

   public List list() {
      long var1 = System.currentTimeMillis();

      ArrayList var4;
      try {
         ArrayList var3 = new ArrayList();
         this.listFilesRecurse(this.base, var3, !this.recurse ? 1 : Integer.MAX_VALUE);
         var4 = var3;
      } finally {
         long var8 = System.currentTimeMillis() - var1;
         if (var8 > 200L && this.recurse) {
            GlobalLog.getLogger().debug("*** Lengthy recursive directory listing: %s: %dms", this.base, var8);
         }
      }

      return var4;
   }

   private void listFilesRecurse(File var1, List var2, int var3) {
      if (var1.isDirectory()) {
         if (var3 == 0) {
            return;
         }

         if (this.blacklistedDirnames.contains(var1.getName())) {
            return;
         }

         for (String var7 : var1.list()) {
            this.listFilesRecurse(new File(var1, var7), var2, var3 - 1);
         }
      } else if (var1.isFile() && (this.pattern == null || this.pattern.matcher(var1.getName()).matches())) {
         var2.add(var1);
      }
   }

   @Override
   public Iterator iterator() {
      return new DirectoryEnumerator.DirIter();
   }

   class DirIter implements Iterator {
      Deque tba = new ArrayDeque();
      File curdir;
      String[] curnames = new String[0];
      int index;
      File curfile;

      DirIter() {
         this.tba.add(DirectoryEnumerator.this.base);
         this.curfile = this.prepareNext();
      }

      @Override
      public boolean hasNext() {
         return this.curfile != null;
      }

      public File next() {
         if (this.curfile == null) {
            throw new NoSuchElementException();
         } else {
            File var1 = this.curfile;
            this.curfile = this.prepareNext();
            return var1;
         }
      }

      private File prepareNext() {
         while (true) {
            if (this.index >= this.curnames.length) {
               if (this.tba.isEmpty()) {
                  return null;
               }

               this.curdir = (File)this.tba.remove();
               this.curnames = this.curdir.list();
               if (this.curnames == null) {
                  this.curnames = new String[0];
               }

               this.index = 0;
            }

            while (this.index < this.curnames.length) {
               String var1 = this.curnames[this.index++];
               File var2 = new File(this.curdir, var1);
               if (!var2.isDirectory()) {
                  if (var2.isFile() && (DirectoryEnumerator.this.pattern == null || DirectoryEnumerator.this.pattern.matcher(var1).matches())) {
                     return var2;
                  }
               } else if (!DirectoryEnumerator.this.blacklistedDirnames.contains(var1)) {
                  this.tba.add(var2);
               }
            }
         }
      }
   }
}
