package com.pnfsoftware.jeb.util.io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;

class IO$2 extends SimpleFileVisitor {
   IO$2(ArrayList var1, ArrayList var2) {
      this.val$files = var1;
      this.val$directories = var2;
   }

   public FileVisitResult visitFile(Path var1, BasicFileAttributes var2) {
      this.val$files.add(var1);
      return FileVisitResult.CONTINUE;
   }

   public FileVisitResult postVisitDirectory(Path var1, IOException var2) {
      this.val$directories.add(var1);
      return FileVisitResult.CONTINUE;
   }
}
