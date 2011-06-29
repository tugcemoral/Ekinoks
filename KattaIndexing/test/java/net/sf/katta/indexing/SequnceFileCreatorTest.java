/**
 * Copyright 2008 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.sf.katta.indexing;

import static org.junit.Assert.assertTrue;

import java.io.File;

import junit.framework.Assert;

import org.junit.Test;

public class SequnceFileCreatorTest {

  @Test
  public void testCreateSequnceFile() throws Exception {

    SequenceFileCreator creator = new SequenceFileCreator();
    String path = "./build/extras/indexing/tmp/SequenceFileCreator/sequenceFile";
    org.apache.hadoop.fs.FileUtil.fullyDelete(new File(path).getParentFile());
    Assert.assertFalse(new File(path).exists());

    String textPath = SearchPathUtil.findPath("sample-data/texts/alice.txt", "../../sample-data/texts/alice.txt");
    String sampleText = SequenceFileCreator.getSampleText(textPath);
    int num = 1000000;
    creator.create(path, sampleText, num);
    assertTrue(new File(path).exists());

  }

}
