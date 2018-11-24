package com.tpEspecialArq2018;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
   TestRestUsuarios.class,
   TestRestTrabajos.class,
   TestRestEvaluaciones.class
})

public class TestRestSuite {}
