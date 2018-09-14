package com.bobo.anno;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Vector;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AnnoApplicationTests {

	@Test
	public void contextLoads() {
		new LinkedList<>();
		new ArrayList<>();
		new HashMap<>();
	}

}
