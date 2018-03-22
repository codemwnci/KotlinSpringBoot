package codemwnci.bootdemo

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class BootdemoApplication

fun main(args: Array<String>) {
    SpringApplication.run(BootdemoApplication::class.java, *args)
}
